package ru.practicum.android.diploma.ui.filter.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.navigation.fragment.findNavController
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.data.models.EMPTY_PARAM_NUM
import ru.practicum.android.diploma.data.models.EMPTY_PARAM_SRT
import ru.practicum.android.diploma.databinding.FragmentFilterSettingsBinding
import ru.practicum.android.diploma.domain.models.settings.SearchSettings
import ru.practicum.android.diploma.presentation.settings.viewmodels.SettingsViewModel
import ru.practicum.android.diploma.util.BindingFragment

class FilterFragment : BindingFragment<FragmentFilterSettingsBinding>() {

    private val settingsViewModel by viewModel<SettingsViewModel>()
    private var currentSalary = EMPTY_PARAM_SRT
    private var boxChecked = false
    private var isPlaceCanDelete = false
    private var isIndustryCanDelete = false
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentFilterSettingsBinding {
        return FragmentFilterSettingsBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bind()
    }

    private fun bind() {
        settingsViewModel.getSettings()
        settingsViewModel.screenState.observe(viewLifecycleOwner) {
            processingState(it)
        }
        settingsViewModel.isSettingIsNotEmpty.observe(viewLifecycleOwner) {
            binding.clearFilterSettingsButton.isVisible = it
        }
        settingsViewModel.isSettingsModified.observe(viewLifecycleOwner) {
            binding.applyFilterSettingsButton.isVisible = it
        }
        binding.expectedSalaryLayout.doAfterTextChanged { text ->
            if (currentSalary != text.toString()) {
                currentSalary = text.toString()
                settingsViewModel.saveSalary(currentSalary)
                setVisibilityCloseIcon(currentSalary)
            }
        }
        settingsViewModel.boxChecked.observe(viewLifecycleOwner) {
            binding.doNotShowWithoutSalaryCheckbox.isChecked = it
            boxChecked = it
        }
        settingsViewModel.isPlaceCanDelete.observe(viewLifecycleOwner) {
            isPlaceCanDelete = it
        }
        settingsViewModel.isIndustryCanDelete.observe(viewLifecycleOwner) {
            isIndustryCanDelete = it
        }
        setOnClicks()
    }

    private fun processingState(settings: SearchSettings) {
        val location = StringBuilder("")
        with(binding) {
            boxChecked = settings.isSalarySpecified
            doNotShowWithoutSalaryCheckbox.isChecked = settings.isSalarySpecified
            if (settings.industry.industryName.isNotEmpty()) {
                industryLayout.setText(settings.industry.industryName)
                industry.setEndIconDrawable(R.drawable.ic_close)
            } else {
                industryLayout.setText(settings.industry.industryName)
                industry.setEndIconDrawable(R.drawable.ic_arrow_forward)
            }
            if (settings.country.countryId.isNotEmpty()) {
                workplace.setEndIconDrawable(R.drawable.ic_close)
                location.append(settings.country.countryName)
            } else {
                workplace.setEndIconDrawable(R.drawable.ic_arrow_forward)
            }
            if (settings.place.areaId.isNotEmpty()) {
                location.append(", ${settings.place.areaName}")
            }
            workplaceLayout.setText(location)
            setSalary(settings)
        }
    }

    private fun setSalary(setting: SearchSettings) {
        val newSalary = if (setting.salary == EMPTY_PARAM_NUM) {
            EMPTY_PARAM_SRT
        } else {
            setting.salary.toString()
        }
        binding.expectedSalaryLayout.setText(newSalary)
        setVisibilityCloseIcon(newSalary)
    }

    private fun setVisibilityCloseIcon(text: String) {
        val hasFocus = binding.expectedSalaryLayout.hasFocus()
        binding.expectedSalary.isEndIconVisible = text.isNotEmpty() && hasFocus
    }

    private fun setOnClicks() = with(binding) {
        expectedSalary.setEndIconOnClickListener {
            binding.expectedSalaryLayout.setText(EMPTY_PARAM_SRT)
        }
        expectedSalaryLayout.setOnFocusChangeListener { vies, hasFocus ->
            setVisibilityCloseIcon(currentSalary)
        }
        workplace.setEndIconOnClickListener {
            if (isPlaceCanDelete) {
                workplace.setEndIconDrawable(R.drawable.ic_arrow_forward)
                settingsViewModel.deletePlace()
            } else {
                findNavController().navigate(R.id.action_filterFragment_to_localityTypeFragment)
            }
        }
        industry.setEndIconOnClickListener {
            if (isIndustryCanDelete) {
                industry.setEndIconDrawable(R.drawable.ic_arrow_forward)
                settingsViewModel.deleteIndustry()
            } else {
                findNavController().navigate(R.id.action_filterFragment_to_industryPickerFragment)
            }
        }
        workplaceLayout.setOnClickListener {
            findNavController().navigate(R.id.action_filterFragment_to_localityTypeFragment)
        }
        industryLayout.setOnClickListener {
            findNavController().navigate(R.id.action_filterFragment_to_industryPickerFragment)
        }
        doNotShowWithoutSalary.setOnClickListener {
            binding.expectedSalaryLayout.clearFocus()
            boxChecked = !boxChecked
            binding.doNotShowWithoutSalaryCheckbox.isChecked = boxChecked
            settingsViewModel.savedIsSalarySpecified(boxChecked)
            setVisibilityCloseIcon(currentSalary)
        }
        applyFilterSettingsButton.setOnClickListener {
            settingsViewModel.saveSettingsByClickConfirm()
            findNavController().navigateUp()
        }
        clearFilterSettingsButton.setOnClickListener {
            settingsViewModel.deletedSettings()
        }
        backButton.setOnClickListener {
            findNavController().navigateUp()
        }
        doNotShowWithoutSalaryCheckbox.setOnClickListener {
            binding.expectedSalaryLayout.clearFocus()
            boxChecked = !boxChecked
            binding.doNotShowWithoutSalaryCheckbox.isChecked = boxChecked
            settingsViewModel.savedIsSalarySpecified(boxChecked)
            setVisibilityCloseIcon(currentSalary)
        }
    }
}

package ru.practicum.android.diploma.ui.filter.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.data.models.EMPTY_PARAM_NUM
import ru.practicum.android.diploma.data.models.EMPTY_PARAM_SRT
import ru.practicum.android.diploma.data.models.ValuesSearchId
import ru.practicum.android.diploma.databinding.FragmentFilterSettingsBinding
import ru.practicum.android.diploma.domain.models.settings.SearchSettings
import ru.practicum.android.diploma.presentation.settings.viewmodels.SettingsViewModel
import ru.practicum.android.diploma.util.BindingFragment

class FilterFragment : BindingFragment<FragmentFilterSettingsBinding>() {

    private val settingsViewModel by viewModel<SettingsViewModel>()
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentFilterSettingsBinding {
        return FragmentFilterSettingsBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bind()
        val callback = requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    findNavController().navigateUp()
                }

            }
        )
    }

    private fun bind() {
        settingsViewModel.getSettings()
        settingsViewModel.screenState.observe(viewLifecycleOwner) {
            processingState(it)
        }
        settingsViewModel.isSettingsModified.observe(viewLifecycleOwner) {
            binding.applyFilterSettingsButton.isVisible = it
        }
        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                val text = s.toString()
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                settingsViewModel.updateSalary(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {
                val text = s.toString()
            }
        }
        binding.expectedSalaryLayout.addTextChangedListener(textWatcher)
        setOnClicks()
    }

    private fun processingState(settings: SearchSettings) {
        binding.clearFilterSettingsButton.isVisible = isSettingsEmpty(settings)
        if (isSettingsEmpty(settings)) {
            val salary = if (settings.salary == EMPTY_PARAM_NUM) {
                EMPTY_PARAM_SRT
            } else {
                settings.salary.toString()
            }
            with(binding) {
                doNotShowWithoutSalaryCheckbox.isChecked = settings.isSalarySpecified
                expectedSalaryLayout.setText(salary)
                industryLayout.setText(settings.industry.industryName)
                workplaceLayout.setText("${settings.country.countryName}, ${settings.place.areaName}")
            }
        }
    }

    private fun isSettingsEmpty(settings: SearchSettings): Boolean {
        val result = !(
            !settings.isSalarySpecified
                && settings.salary == EMPTY_PARAM_NUM
                && settings.settingsId == ValuesSearchId.BASE
                && settings.country.countryId == EMPTY_PARAM_SRT
                && settings.place.areaId == EMPTY_PARAM_SRT
            )
        return result
    }

    private fun setOnClicks() = with(binding) {
        val listner = onClick()
        workplaceLayout.setOnClickListener(listner)
        industryLayout.setOnClickListener(listner)
        doNotShowWithoutSalaryCheckbox.setOnClickListener(listner)
        applyFilterSettingsButton.setOnClickListener(listner)
        clearFilterSettingsButton.setOnClickListener(listner)
        backButton.setOnClickListener(listner)
    }

    private fun onClick(): View.OnClickListener {
        return View.OnClickListener {
            when (it.id) {
                R.id.workplace_layout -> {
                    findNavController().navigate(R.id.action_filterFragment_to_localityTypeFragment)
                }

                R.id.industry_layout -> {
                    findNavController().navigate(R.id.action_filterFragment_to_industryPickerFragment)
                }

                R.id.do_not_show_without_salary_checkbox -> {
                    settingsViewModel.savedIsSalarySpecified(binding.doNotShowWithoutSalaryCheckbox.isChecked)
                }

                R.id.apply_filter_settings_button -> {
                    settingsViewModel.saveSettingsByClickConfirm()
                    findNavController().navigateUp()
                }

                R.id.clear_filter_settings_button -> {
                    settingsViewModel.deletedSettings()
                }

                R.id.back_button -> {
                    findNavController().navigateUp()
                }

            }
        }
    }
}

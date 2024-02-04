package ru.practicum.android.diploma.ui.filter.fragment.placeofwork

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.navigation.fragment.findNavController
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.databinding.FragmentLocalityTypeBinding
import ru.practicum.android.diploma.presentation.settings.models.LocalityTypeScreenState
import ru.practicum.android.diploma.presentation.settings.viewmodels.LocalityTypeViewModel
import ru.practicum.android.diploma.util.BindingFragment

class LocalityTypeFragment : BindingFragment<FragmentLocalityTypeBinding>() {

    private val viewModel by viewModel<LocalityTypeViewModel>()
    override fun createBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentLocalityTypeBinding {
        return FragmentLocalityTypeBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bind()
        viewModel.updateState()
        viewModel.screenState.observe(viewLifecycleOwner) {
            render(it)
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.updateState()
    }

    private fun render(screenState: LocalityTypeScreenState) {
        when (screenState) {
            is LocalityTypeScreenState.Content -> {
                with(binding) {
                    etCountryLayout.setText(screenState.country.countryName)
                    etRegionLayout.setText(screenState.place.areaName)
                }
            }

            LocalityTypeScreenState.Empty -> {
                with(binding) {
                    etCountryLayout.text?.clear()
                    etRegionLayout.text?.clear()
                }
            }
        }
    }

    private fun setButtonVisibility(text: CharSequence?) {
        binding.btnApply.isVisible = !text.isNullOrEmpty()
    }

    private fun bind() {
        with(binding) {
            etCountryLayout.doOnTextChanged { text, _, _, _ ->
                if (text.isNullOrEmpty()) {
                    etCountry.setEndIconDrawable(R.drawable.ic_arrow_forward)
                } else {
                    etCountry.setEndIconDrawable(R.drawable.ic_close)
                }
                setButtonVisibility(text)
            }
            etRegionLayout.doOnTextChanged { text, _, _, _ ->
                if (text.isNullOrEmpty()) {
                    etRegion.setEndIconDrawable(R.drawable.ic_arrow_forward)
                } else {
                    etRegion.setEndIconDrawable(R.drawable.ic_close)
                }
                setButtonVisibility(text)
            }
            etCountry.setEndIconOnClickListener {
                viewModel.deleteCountryFromSettings()
                viewModel.deleteCityFromSettings()
                etCountryLayout.text?.clear()
                viewModel.updateState()
            }
            etRegion.setEndIconOnClickListener {
                viewModel.deleteCityFromSettings()
                etRegionLayout.text?.clear()
                viewModel.updateState()
            }
            etCountryLayout.setOnClickListener {
                findNavController().navigate(R.id.action_localityTypeFragment_to_countryPickerFragment2)
            }
            etRegionLayout.setOnClickListener {
                findNavController().navigate(R.id.action_localityTypeFragment_to_cityPickerFragment)
            }
            btnApply.setOnClickListener {
                findNavController().popBackStack()
            }
            btnBack.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }
}

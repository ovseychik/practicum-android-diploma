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
import ru.practicum.android.diploma.databinding.FragmentLocationTypeBinding
import ru.practicum.android.diploma.presentation.settings.models.LocationTypeScreenState
import ru.practicum.android.diploma.presentation.settings.viewmodels.LocationTypeViewModel
import ru.practicum.android.diploma.util.BindingFragment

class LocationTypeFragment : BindingFragment<FragmentLocationTypeBinding>() {

    private val viewModel by viewModel<LocationTypeViewModel>()
    override fun createBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentLocationTypeBinding {
        return FragmentLocationTypeBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bind()
        setupTextListeners()
        viewModel.updateState()
        viewModel.screenState.observe(viewLifecycleOwner) {
            render(it)
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.updateState()
    }

    private fun render(screenState: LocationTypeScreenState) {
        when (screenState) {
            is LocationTypeScreenState.Content -> {
                with(binding) {
                    etCountryLayout.setText(screenState.country.countryName)
                    etRegionLayout.setText(screenState.place.areaName)
                    btnApply.isVisible = true
                }
            }

            LocationTypeScreenState.Empty -> {
                with(binding) {
                    etCountryLayout.text?.clear()
                    etRegionLayout.text?.clear()
                    btnApply.isVisible = false
                }
            }
        }
    }

    private fun bind() {
        with(binding) {
            etCountry.setEndIconOnClickListener {
                viewModel.deleteCountryFromSettings()
                viewModel.deleteCityFromSettings()
                viewModel.updateState()
            }
            etRegion.setEndIconOnClickListener {
                viewModel.deleteCityFromSettings()
                viewModel.updateState()
            }
            etCountryLayout.setOnClickListener {
                findNavController().navigate(R.id.action_locationTypeFragment_to_countryPickerFragment2)
            }
            etRegionLayout.setOnClickListener {
                findNavController().navigate(R.id.action_locationTypeFragment_to_cityPickerFragment)
            }
            btnApply.setOnClickListener {
                findNavController().popBackStack()
            }
            btnBack.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    private fun setupTextListeners() {
        with(binding) {
            etCountryLayout.doOnTextChanged { text, _, _, _ ->
                if (text.isNullOrEmpty()) {
                    etCountry.setEndIconDrawable(R.drawable.ic_arrow_forward)
                } else {
                    etCountry.setEndIconDrawable(R.drawable.ic_close)
                }
            }
            etRegionLayout.doOnTextChanged { text, _, _, _ ->
                if (text.isNullOrEmpty()) {
                    etRegion.setEndIconDrawable(R.drawable.ic_arrow_forward)
                } else {
                    etRegion.setEndIconDrawable(R.drawable.ic_close)
                }
            }
        }
    }
}

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
        viewModel.screenState.observe(viewLifecycleOwner) {
            render(it)
        }
    }

    private fun render(screenState: LocalityTypeScreenState) {
        when (screenState) {
            is LocalityTypeScreenState.Content -> {
                binding.etCountryLayout.setText(screenState.country.countryName)
                binding.etRegionLayout.setText(screenState.place.areaName)
            }

            LocalityTypeScreenState.Empty -> {

            }
        }
    }

    private fun setButtonVisibility(text: CharSequence?) {
        binding.btnApply.isVisible = !text.isNullOrEmpty()
    }

    private fun bind() {
        with(binding) {
            etCountryLayout.doOnTextChanged { text, _, _, _ ->
                etCountry.setEndIconDrawable(R.drawable.ic_close)
                setButtonVisibility(text)
            }
            etRegionLayout.doOnTextChanged { text, _, _, _ ->
                etRegion.setEndIconDrawable(R.drawable.ic_close)
                setButtonVisibility(text)
            }
            etCountry.setEndIconOnClickListener {
                binding.etCountryLayout.text?.clear()
                // viewModel.deleteCountry
            }
            etRegion.setEndIconOnClickListener {
                binding.etRegionLayout.text?.clear()
                // viewmodel.deleteCity
            }
            etCountryLayout.setOnClickListener {
                // findNavController().navigate(R.id.action_localityTypeFragment_to_countryPickerFragment)
            }
            etRegionLayout.setOnClickListener {
                findNavController().navigate(R.id.action_localityTypeFragment_to_cityPickerFragment)
            }
            btnApply.setOnClickListener {
                findNavController().popBackStack()
            }
        }

    }
}

package ru.practicum.android.diploma.ui.filter.fragment.countrypicker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.practicum.android.diploma.databinding.FragmentCountryPickerBinding
import ru.practicum.android.diploma.domain.models.guides.Country
import ru.practicum.android.diploma.presentation.settings.adapters.LocalityAdapter
import ru.practicum.android.diploma.presentation.settings.adapters.LocalityAdapterItem
import ru.practicum.android.diploma.presentation.settings.models.CountriesScreenState
import ru.practicum.android.diploma.presentation.settings.viewmodels.CountriesViewModel
import ru.practicum.android.diploma.util.BindingFragment
import ru.practicum.android.diploma.util.debounce

class CountryPickerFragment : BindingFragment<FragmentCountryPickerBinding>() {

    private val viewModel by viewModel<CountriesViewModel>()
    private var countryClickDebounce: ((LocalityAdapterItem.CountryItem) -> Unit)? = null
    private val countriesAdapter = LocalityAdapter { country ->
        countryClickDebounce?.let {
            it(country as LocalityAdapterItem.CountryItem)
            findNavController().popBackStack()
        }
    }

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentCountryPickerBinding {
        return FragmentCountryPickerBinding.inflate(layoutInflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bind()
        viewModel.screenState.observe(viewLifecycleOwner) {
            render(it)
        }
    }

    private fun bind() {
        with(binding) {
            rvCountry.adapter = countriesAdapter
            btnBack.setOnClickListener {
                findNavController().popBackStack()
            }
        }
        countryClickDebounce =
            debounce(CLICK_DEBOUNCE_DELAY_MILLIS, lifecycleScope, false) {
                viewModel.saveCountry(it.country)
            }
    }

    private fun render(screenState: CountriesScreenState) {
        when (screenState) {
            is CountriesScreenState.Content -> showContent(screenState.countriesList)
            is CountriesScreenState.Error -> showErrorState(resources.getString(screenState.message))
            CountriesScreenState.Loading -> showLoading()
            is CountriesScreenState.NoInternet -> showNoInternetState(resources.getString(screenState.message))
        }
    }

    private fun showContent(countries: List<Country>) {
        binding.rvCountry.isVisible = true
        countriesAdapter.addCountries(countries)
    }

    private fun showErrorState(message: String) {
        with(binding) {
            rvCountry.isVisible = false
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
            // добавить в верстку error (на макете нет)
        }
    }

    private fun showNoInternetState(message: String) {
        with(binding) {
            rvCountry.isVisible = false
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
            // добавить в верстку no internet(на макете нет)
        }
    }

    private fun showLoading() {
        with(binding) {
            rvCountry.isVisible = false
            Toast.makeText(requireContext(), "loading", Toast.LENGTH_SHORT).show()
            // добавить в верстку loading (на макете нет)
        }
    }

    companion object {
        private const val CLICK_DEBOUNCE_DELAY_MILLIS = 300L
    }
}

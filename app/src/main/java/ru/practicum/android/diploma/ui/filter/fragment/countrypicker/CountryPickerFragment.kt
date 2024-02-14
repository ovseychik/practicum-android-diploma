package ru.practicum.android.diploma.ui.filter.fragment.countrypicker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.databinding.FragmentCountryPickerBinding
import ru.practicum.android.diploma.domain.models.guides.Country
import ru.practicum.android.diploma.presentation.settings.adapters.LocationAdapter
import ru.practicum.android.diploma.presentation.settings.adapters.LocationAdapterItem
import ru.practicum.android.diploma.presentation.settings.models.CountriesScreenState
import ru.practicum.android.diploma.presentation.settings.viewmodels.CountriesViewModel
import ru.practicum.android.diploma.util.BindingFragment
import ru.practicum.android.diploma.util.debounce

class CountryPickerFragment : BindingFragment<FragmentCountryPickerBinding>() {

    private val viewModel by viewModel<CountriesViewModel>()
    private var countryClickDebounce: ((LocationAdapterItem.CountryItem) -> Unit)? = null
    private val countriesAdapter = LocationAdapter { country ->
        countryClickDebounce?.let {
            it(country as LocationAdapterItem.CountryItem)
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
        viewModel.getCountries()
        viewModel.screenState.observe(viewLifecycleOwner) {
            render(it)
        }
    }

    private fun bind() {
        with(binding) {
            rvCountries.adapter = countriesAdapter
            btnBack.setOnClickListener {
                findNavController().popBackStack()
            }
        }
        countryClickDebounce =
            debounce(CLICK_DEBOUNCE_DELAY_MILLIS, lifecycleScope, false) {
                viewModel.saveCountry(it.country)
                findNavController().popBackStack()
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
        with(binding) {
            rvCountries.isVisible = true
            ivPicPlaceholder.isVisible = false
            tvErrorPlaceholder.isVisible = false
            pbCircle.isVisible = false
        }
        countriesAdapter.addCountries(countries)
    }

    private fun showErrorState(message: String) {
        with(binding) {
            rvCountries.isVisible = false
            ivPicPlaceholder.isVisible = true
            tvErrorPlaceholder.isVisible = true
            pbCircle.isVisible = false
            ivPicPlaceholder.setImageResource(R.drawable.ic_server_error)
            tvErrorPlaceholder.text = message
        }
    }

    private fun showNoInternetState(message: String) {
        with(binding) {
            rvCountries.isVisible = false
            ivPicPlaceholder.isVisible = true
            tvErrorPlaceholder.isVisible = true
            pbCircle.isVisible = false
            ivPicPlaceholder.setImageResource(R.drawable.ic_no_internet_pic)
            tvErrorPlaceholder.text = message
        }
    }

    private fun showLoading() {
        with(binding) {
            rvCountries.isVisible = false
            ivPicPlaceholder.isVisible = false
            tvErrorPlaceholder.isVisible = false
            pbCircle.isVisible = true
        }
    }

    companion object {
        private const val CLICK_DEBOUNCE_DELAY_MILLIS = 300L
    }
}

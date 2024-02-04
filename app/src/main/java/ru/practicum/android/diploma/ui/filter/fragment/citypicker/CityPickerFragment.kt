package ru.practicum.android.diploma.ui.filter.fragment.citypicker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.databinding.FragmentCityPickerBinding
import ru.practicum.android.diploma.domain.models.guides.PlaceItem
import ru.practicum.android.diploma.presentation.settings.adapters.LocalityAdapter
import ru.practicum.android.diploma.presentation.settings.adapters.LocalityAdapterItem
import ru.practicum.android.diploma.presentation.settings.models.PlacesScreenState
import ru.practicum.android.diploma.presentation.settings.viewmodels.PlacesViewModel
import ru.practicum.android.diploma.util.BindingFragment
import ru.practicum.android.diploma.util.debounce

class CityPickerFragment : BindingFragment<FragmentCityPickerBinding>() {

    private val viewModel by viewModel<PlacesViewModel>()
    private var cityClickDebounce: ((LocalityAdapterItem.City) -> Unit)? = null
    private val placesAdapter = LocalityAdapter { city ->
        cityClickDebounce?.let {
            it(city as LocalityAdapterItem.City)
        }
    }

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentCityPickerBinding {
        return FragmentCityPickerBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.screenState.observe(viewLifecycleOwner) {
            render(it)
        }
        viewModel.getPlaces()
        bind()
    }

    private fun render(screenState: PlacesScreenState) {
        when (screenState) {
            is PlacesScreenState.Content -> showContent(screenState.placesList)
            is PlacesScreenState.Empty -> showEmptyState(screenState.message)
            is PlacesScreenState.Error -> showServerErrorState(screenState.message)
            is PlacesScreenState.Loading -> showLoadingState()
            is PlacesScreenState.NoInternet -> showNoInternetState(screenState.message)
        }
    }

    private fun showContent(placesList: List<PlaceItem>) {
        with(binding) {
            rvSearchResult.isVisible = true
            tvErrorPlaceholder.isVisible = false
            ivPicPlaceholder.isVisible = false
            pbCircle.isVisible = false
        }
        placesAdapter.clearData()
        placesAdapter.addPlaces(placesList)
    }

    private fun showEmptyState(message: Int) {
        with(binding) {
            rvSearchResult.isVisible = false
            tvErrorPlaceholder.isVisible = true
            ivPicPlaceholder.isVisible = true
            pbCircle.isVisible = false
            ivPicPlaceholder.setImageResource(R.drawable.ic_nothing_found_pic)
            tvErrorPlaceholder.text = getString(message)
        }
    }

    private fun showServerErrorState(message: Int) {
        with(binding) {
            rvSearchResult.isVisible = false
            tvErrorPlaceholder.isVisible = true
            ivPicPlaceholder.isVisible = true
            pbCircle.isVisible = false
            ivPicPlaceholder.setImageResource(R.drawable.ic_nothing_found_pic)
            tvErrorPlaceholder.text = getString(message)
        }
    }

    private fun showLoadingState() {
        with(binding) {
            rvSearchResult.isVisible = false
            tvErrorPlaceholder.isVisible = false
            ivPicPlaceholder.isVisible = false
            pbCircle.isVisible = true
        }
    }

    private fun showNoInternetState(message: Int) {
        with(binding) {
            rvSearchResult.isVisible = false
            tvErrorPlaceholder.isVisible = true
            ivPicPlaceholder.isVisible = true
            pbCircle.isVisible = false
            ivPicPlaceholder.setImageResource(R.drawable.ic_no_internet_pic)
            tvErrorPlaceholder.text = getString(message)
        }
    }

    private fun bind() {
        with(binding) {
            etSearch.doAfterTextChanged { query ->
                if (query.isNullOrEmpty()) {
                    btnClear.setImageResource(R.drawable.ic_search)
                    viewModel.getPlaces()
                } else {
                    btnClear.setImageResource(R.drawable.ic_close)
                    viewModel.getFilteredPlaces(query.toString())
                }
            }
            btnClear.setOnClickListener {
                etSearch.text.clear()
            }
            rvSearchResult.adapter = placesAdapter
            btnBack.setOnClickListener {
                findNavController().popBackStack()
            }
        }
        cityClickDebounce = debounce(CLICK_DEBOUNCE_DELAY_MILLIS, lifecycleScope, false) {
            viewModel.savePlace(it.place)
            findNavController().popBackStack()
        }
    }

    companion object {
        private const val CLICK_DEBOUNCE_DELAY_MILLIS = 300L
    }
}

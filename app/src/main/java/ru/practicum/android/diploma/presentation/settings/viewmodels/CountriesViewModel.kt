package ru.practicum.android.diploma.presentation.settings.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.data.models.EMPTY_PARAM_SRT
import ru.practicum.android.diploma.domain.api.guides.PlacesInteractor
import ru.practicum.android.diploma.domain.models.SearchResultData
import ru.practicum.android.diploma.domain.models.guides.Country
import ru.practicum.android.diploma.domain.models.guides.PlaceItem
import ru.practicum.android.diploma.presentation.settings.models.CountriesScreenState

class CountriesViewModel(private val placesInteractor: PlacesInteractor) : ViewModel() {

    private var _screenState: MutableLiveData<CountriesScreenState> = MutableLiveData()
    val screenState: LiveData<CountriesScreenState> = _screenState

    fun getCountries() {
        _screenState.postValue(CountriesScreenState.Loading)
        viewModelScope.launch {
            placesInteractor.getCountries().collect {
                processingResult(it)
            }
        }
    }

    fun saveCountry(country: Country) {
        if (placesInteractor.getCountryFromSettings() != country) {
            placesInteractor.setCountryInSettings(country)
            placesInteractor.setPlaceInSettings(PlaceItem(EMPTY_PARAM_SRT, EMPTY_PARAM_SRT))
        }
    }

    private fun processingResult(result: SearchResultData<Set<Country>>) {
        when (result) {
            is SearchResultData.Data -> {
                if (result.value != null) {
                    _screenState.postValue(CountriesScreenState.Content(result.value.toList()))
                } else {
                    _screenState.postValue(CountriesScreenState.Error(R.string.server_error))
                }
            }

            is SearchResultData.ErrorServer -> {
                _screenState.postValue(CountriesScreenState.Error(R.string.server_error))
            }

            is SearchResultData.NoInternet -> {
                _screenState.postValue(CountriesScreenState.NoInternet(R.string.no_internet))
            }

            else -> {}
        }
    }
}

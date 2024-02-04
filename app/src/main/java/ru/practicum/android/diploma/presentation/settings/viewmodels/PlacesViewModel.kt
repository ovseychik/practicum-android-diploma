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
import ru.practicum.android.diploma.domain.models.settings.setDefault
import ru.practicum.android.diploma.presentation.settings.models.PlacesScreenState

class PlacesViewModel(private val placesInteractor: PlacesInteractor) : ViewModel() {

    private var _screenState: MutableLiveData<PlacesScreenState> = MutableLiveData()
    val screenState: LiveData<PlacesScreenState> = _screenState
    private val listPlaces = mutableListOf<PlaceItem>()
    private var selectedCountry = setDefault().country
    private var mapPlaces = mapOf<Country, List<PlaceItem>>()

    fun getPlaces() {
        viewModelScope.launch {
            listPlaces.clear()
            _screenState.postValue(PlacesScreenState.Loading)
            selectedCountry = placesInteractor.getCountryFromSettings()
            if (selectedCountry.countryId != EMPTY_PARAM_SRT) {
                placesInteractor.getPlacesById(selectedCountry.countryId).collect {
                    processingResult(it)
                }
            } else {
                placesInteractor.getAllPlaces().collect {
                    processingResultForAllPlaces(it)
                }
            }
        }
    }

    fun getFilteredPlaces(query: String) {
        val filteredList = listPlaces
            .filter { query.length <= it.areaName.length }
            .filter { it.areaName.substring(0, query.length).lowercase() == query.lowercase() }
        _screenState.postValue(PlacesScreenState.Loading)
        if (filteredList.isEmpty()) {
            _screenState.postValue(PlacesScreenState.Empty(R.string.region_not_found))
        } else {
            _screenState.postValue(PlacesScreenState.Content(filteredList))
        }
    }

    fun savePlace(place: PlaceItem) {
        placesInteractor.setPlaceInSettings(place)
        if (selectedCountry.countryId == EMPTY_PARAM_SRT) {
            selectedCountry = placesInteractor.getCountryById(place.areaId, mapPlaces)
            placesInteractor.setCountryInSettings(selectedCountry)
        }
    }

    private fun processingResult(result: SearchResultData<List<PlaceItem>>) {
        when (result) {
            is SearchResultData.Data -> {
                listPlaces.addAll(result.value!!)
                listPlaces.sortBy { it.areaName }
                _screenState.postValue(PlacesScreenState.Content(listPlaces))
            }

            is SearchResultData.NoInternet -> {
                _screenState.postValue(PlacesScreenState.NoInternet(R.string.no_internet))
            }

            is SearchResultData.ErrorServer -> {
                _screenState.postValue(PlacesScreenState.Error(R.string.server_error))
            }

            is SearchResultData.Empty -> {
                _screenState.postValue(PlacesScreenState.Empty(R.string.region_not_found))
            }
        }
    }

    private fun processingResultForAllPlaces(result: SearchResultData<Map<Country, List<PlaceItem>>>) {
        when (result) {
            is SearchResultData.Data -> {
                mapPlaces = result.value!!
                result.value.forEach { key, items ->
                    listPlaces.addAll(items)
                }
                listPlaces.sortBy { it.areaName }
                _screenState.postValue(PlacesScreenState.Content(listPlaces))
            }

            is SearchResultData.NoInternet -> {
                _screenState.postValue(PlacesScreenState.NoInternet(R.string.no_internet))
            }

            is SearchResultData.ErrorServer -> {
                _screenState.postValue(PlacesScreenState.Error(R.string.server_error))
            }

            is SearchResultData.Empty -> {
                _screenState.postValue(PlacesScreenState.Empty(R.string.region_not_found))
            }
        }
    }
}

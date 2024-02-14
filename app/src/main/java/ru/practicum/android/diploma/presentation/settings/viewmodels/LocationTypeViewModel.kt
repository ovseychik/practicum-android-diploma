package ru.practicum.android.diploma.presentation.settings.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.domain.api.guides.PlacesInteractor
import ru.practicum.android.diploma.domain.models.SearchResultData
import ru.practicum.android.diploma.domain.models.guides.Country
import ru.practicum.android.diploma.domain.models.guides.PlaceItem
import ru.practicum.android.diploma.presentation.settings.models.LocationTypeScreenState

class LocationTypeViewModel(private val placesInteractor: PlacesInteractor) : ViewModel() {

    private var place: PlaceItem = placesInteractor.getPlaceFromSettings()
    private var country: Country = placesInteractor.getCountryFromSettings()
    private val _screenState: MutableLiveData<LocationTypeScreenState> = MutableLiveData()
    val screenState: LiveData<LocationTypeScreenState> = _screenState

    init {
        setCountryByPlaceId(place)
    }

    private fun setCountryByPlaceId(placeItem: PlaceItem) {
        if (country.countryId.isEmpty() && placeItem.areaId.isNotEmpty()) {
            viewModelScope.launch(Dispatchers.IO) {
                placesInteractor.getCountryById(placeItem.areaId).collect {
                    if (it is SearchResultData.Data) {
                        if (it.value != null) {
                            country = it.value
                        }
                    }
                }
            }
        }
    }

    fun updateState() {
        country = placesInteractor.getCountryFromSettings()
        place = placesInteractor.getPlaceFromSettings()
        if (place.areaId.isNotEmpty() && country.countryId.isEmpty()) {
            setCountryByPlaceId(place)
        }
        if (place.areaId.isNotEmpty() || country.countryId.isNotEmpty()) {
            _screenState.postValue(LocationTypeScreenState.Content(place, country))
        } else {
            _screenState.postValue(LocationTypeScreenState.Empty)
        }
    }

    fun savePlaceToSettings(placeItem: PlaceItem) {
        placesInteractor.setPlaceInSettings(placeItem)
    }

    fun saveCountryToSettings(countryItem: Country) {
        placesInteractor.setCountryInSettings(countryItem)
    }

    fun deleteCountryFromSettings() {
        placesInteractor.setCountryInSettings(Country(EMPTY_VALUE, EMPTY_VALUE))
    }

    fun deleteCityFromSettings() {
        placesInteractor.setPlaceInSettings(PlaceItem(EMPTY_VALUE, EMPTY_VALUE))
    }

    companion object {
        private const val EMPTY_VALUE = ""
    }
}

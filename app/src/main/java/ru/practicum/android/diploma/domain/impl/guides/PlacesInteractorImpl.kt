package ru.practicum.android.diploma.domain.impl.guides

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.domain.api.guides.PlacesInteractor
import ru.practicum.android.diploma.domain.api.guides.PlacesRepository
import ru.practicum.android.diploma.domain.api.settings.SettingsRepository
import ru.practicum.android.diploma.domain.models.SearchResultData
import ru.practicum.android.diploma.domain.models.guides.Country
import ru.practicum.android.diploma.domain.models.guides.PlaceItem

class PlacesInteractorImpl(
    private val placesRepository: PlacesRepository,
    private val settingsRepository: SettingsRepository
) : PlacesInteractor {
    override suspend fun getAllPlaces(): Flow<SearchResultData<Map<Country, List<PlaceItem>>>> {
        return placesRepository.getAllPlaces()
    }

    override suspend fun getPlacesById(countryId: String): Flow<SearchResultData<List<PlaceItem>>> {
        return placesRepository.getPlacesById(countryId)
    }

    override suspend fun getCountryById(placeId: String): Flow<SearchResultData<Country>> {
        return placesRepository.getCountryById(placeId)
    }

    override fun getCountryById(placeId: String, allPlaces: Map<Country, List<PlaceItem>>): Country {
        return placesRepository.getCountryById(placeId, allPlaces)
    }

    override suspend fun getCountries(): Flow<SearchResultData<Set<Country>>> {
        return placesRepository.getCountries()
    }

    override fun getCountryFromSettings(): Country {
        return settingsRepository.getCountryFromSettings()
    }

    override fun getPlaceFromSettings(): PlaceItem {
        return settingsRepository.getPlaceFromSettings()
    }

    override fun setCountryInSettings(country: Country) {
        settingsRepository.setCountryInSettings(country)
    }

    override fun setPlaceInSettings(place: PlaceItem) {
        settingsRepository.setPlaceInSettings(place)
    }
}

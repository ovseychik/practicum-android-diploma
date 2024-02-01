package ru.practicum.android.diploma.domain.api.guides

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.domain.models.SearchResultData
import ru.practicum.android.diploma.domain.models.guides.Country
import ru.practicum.android.diploma.domain.models.guides.PlaceItem

interface PlacesRepository {
    suspend fun getAllPlaces(): Flow<SearchResultData<Map<Country, List<PlaceItem>>>>
    suspend fun getPlacesById(countryId: String): Flow<SearchResultData<List<PlaceItem>>>
    suspend fun getCountryById(placeId: String): Flow<SearchResultData<Country>>

}

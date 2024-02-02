package ru.practicum.android.diploma.data.network.guide

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.data.NetworkClient
import ru.practicum.android.diploma.data.dto.responses.guides.areas.mapToListPlacesItem
import ru.practicum.android.diploma.domain.api.guides.PlacesRepository
import ru.practicum.android.diploma.domain.models.SearchResultData
import ru.practicum.android.diploma.domain.models.guides.Country
import ru.practicum.android.diploma.domain.models.guides.PlaceItem
import java.net.ConnectException
import java.net.SocketTimeoutException

class PlacesRepositoryImpl(private val client: NetworkClient) : PlacesRepository {

    override suspend fun getAllPlaces(): Flow<SearchResultData<Map<Country, List<PlaceItem>>>> = flow {
        val searchResult = client.getAllAreas()
        val data = searchResult.getOrNull()
        val error = searchResult.exceptionOrNull()
        when {
            data != null -> {
                val mapPlaces = mapToListPlacesItem(data)
                emit(SearchResultData.Data(mapPlaces))
            }

            error is ConnectException -> {
                emit(SearchResultData.NoInternet(R.string.no_internet))
            }

            error is SocketTimeoutException -> {
                emit(SearchResultData.NoInternet(R.string.no_internet))
            }

            error is HttpException -> {
                emit(SearchResultData.ErrorServer(R.string.server_error))
            }
        }
    }

    override suspend fun getPlacesById(countryId: String): Flow<SearchResultData<List<PlaceItem>>> = flow {
        val searchResult = client.getAllAreas()
        val data = searchResult.getOrNull()
        val error = searchResult.exceptionOrNull()
        when {
            data != null -> {
                var listPlace = listOf<PlaceItem>()
                val mapPlaces = mapToListPlacesItem(data).filter { it.key.countryId == countryId }
                    .forEach { if (!it.value.isNullOrEmpty()) listPlace = it.value }
                emit(SearchResultData.Data(listPlace))
            }

            error is ConnectException -> {
                emit(SearchResultData.NoInternet(R.string.no_internet))
            }

            error is SocketTimeoutException -> {
                emit(SearchResultData.NoInternet(R.string.no_internet))
            }

            error is HttpException -> {
                emit(SearchResultData.ErrorServer(R.string.server_error))
            }
        }
    }

    override suspend fun getCountryById(placeId: String): Flow<SearchResultData<Country>> = flow {
        val searchResult = client.getAllAreas()
        val data = searchResult.getOrNull()
        val error = searchResult.exceptionOrNull()
        when {
            data != null -> {
                val mapPlaces = mapToListPlacesItem(data)
                var resultCountry = Country("", "")
                mapPlaces.forEach { key, items ->
                    for (element in items) {
                        if (element.areaId == placeId) {
                            resultCountry = key
                        }
                    }
                }
                emit(SearchResultData.Data(resultCountry))
            }

            error is ConnectException -> {
                emit(SearchResultData.NoInternet(R.string.no_internet))
            }

            error is SocketTimeoutException -> {
                emit(SearchResultData.NoInternet(R.string.no_internet))
            }

            error is HttpException -> {
                emit(SearchResultData.ErrorServer(R.string.server_error))
            }
        }
    }

    override suspend fun getCountries(): Flow<SearchResultData<Set<Country>>> = flow {
        val searchResult = client.getAllAreas()
        val data = searchResult.getOrNull()
        val error = searchResult.exceptionOrNull()
        when {
            data != null -> {
                val mapPlaces = mapToListPlacesItem(data)
                emit(SearchResultData.Data(mapPlaces.keys))
            }

            error is ConnectException -> {
                emit(SearchResultData.NoInternet(R.string.no_internet))
            }

            error is SocketTimeoutException -> {
                emit(SearchResultData.NoInternet(R.string.no_internet))
            }

            error is HttpException -> {
                emit(SearchResultData.ErrorServer(R.string.server_error))
            }
        }
    }

}

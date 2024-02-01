package ru.practicum.android.diploma.data.network.guide

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.data.NetworkClient
import ru.practicum.android.diploma.data.dto.responses.guides.industries.mapToListIndustriesItem
import ru.practicum.android.diploma.domain.api.guides.IndustriesRepository
import ru.practicum.android.diploma.domain.models.SearchResultData
import ru.practicum.android.diploma.domain.models.guides.IndustryItem
import java.net.ConnectException
import java.net.SocketTimeoutException

class IndustriesRepositoryImpl(private val client: NetworkClient) : IndustriesRepository {
    override suspend fun getIndustries(): Flow<SearchResultData<List<IndustryItem>>> = flow {
        val searchResult = client.getIndustries()
        val data = searchResult?.getOrNull()
        val error = searchResult?.exceptionOrNull()
        when {
            data != null -> {
                emit(SearchResultData.Data(mapToListIndustriesItem(data)))
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

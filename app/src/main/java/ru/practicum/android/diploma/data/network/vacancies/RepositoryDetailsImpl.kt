package ru.practicum.android.diploma.data.network.vacancies

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.data.NetworkClient
import ru.practicum.android.diploma.data.dto.DetailsRequest
import ru.practicum.android.diploma.data.dto.responses.vacancy.details.mapToVacancyDetails
import ru.practicum.android.diploma.domain.api.RepositoryDetails
import ru.practicum.android.diploma.domain.models.SearchResultData
import ru.practicum.android.diploma.domain.models.vacancy.VacancyDetails
import java.net.ConnectException
import java.net.SocketTimeoutException

class RepositoryDetailsImpl(private val client: NetworkClient) : RepositoryDetails {
    override suspend fun getVacancyDetails(vacancyId: String): Flow<SearchResultData<VacancyDetails>> = flow {
        val searchResult = client.getCurrentVacancy(DetailsRequest(vacancyId))
        val data = searchResult.getOrNull()
        val error = searchResult.exceptionOrNull()
        when {
            data != null -> {
                emit(SearchResultData.Data(data.mapToVacancyDetails()))
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

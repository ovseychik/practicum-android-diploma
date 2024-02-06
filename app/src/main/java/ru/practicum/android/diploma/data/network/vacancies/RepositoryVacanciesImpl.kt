package ru.practicum.android.diploma.data.network.vacancies

import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.data.NetworkClient
import ru.practicum.android.diploma.data.SettingsStorage
import ru.practicum.android.diploma.data.dto.SearchRequest
import ru.practicum.android.diploma.data.dto.responses.vacancy.list.mapToVacancies
import ru.practicum.android.diploma.domain.api.RepositoryVacancies
import ru.practicum.android.diploma.domain.models.SearchResultData
import ru.practicum.android.diploma.domain.models.vacancy.Vacancies
import java.net.ConnectException
import java.net.SocketTimeoutException

class RepositoryVacanciesImpl(
    private val client: NetworkClient,
    private val settingsStorage: SettingsStorage,
    private val json: Gson
) : RepositoryVacancies {
    override suspend fun getVacancies(query: String, pageNum: Int): Flow<SearchResultData<Vacancies>> = flow {
        val searchSettings = settingsStorage.getSettings()
        val searchResult =
            client.getVacancies(SearchRequest.setSearchOptions(query, pageNum, searchSettings))
        val data = searchResult.getOrNull()
        val error = searchResult.exceptionOrNull()

        when {
            data != null -> {
                if (data.found == 0) {
                    emit(SearchResultData.Empty(R.string.empty))
                } else {
                    emit(SearchResultData.Data(data.mapToVacancies()))
                }
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

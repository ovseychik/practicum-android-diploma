package ru.practicum.android.diploma.data.network

import android.content.SharedPreferences
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.data.dto.SearchRequest
import ru.practicum.android.diploma.data.dto.responses.vacancy.list.mapToVacancies
import ru.practicum.android.diploma.data.models.SearchSettings
import ru.practicum.android.diploma.domain.api.RepositoryVacavcies
import ru.practicum.android.diploma.domain.models.SearchResultData
import ru.practicum.android.diploma.domain.models.vacancy.Vacancies
import java.net.ConnectException

const val SEARCHING_OPTIONS = "searching_options" // заглушка до реализации фичи фильтрации

class RepositoryVacanciesImpl(
    private val client: RetrofitNetworkClient,
    private val settingsPref: SharedPreferences,
    private val json: Gson
) : RepositoryVacavcies {
    override suspend fun getVacancies(text: String, page: Int): Flow<SearchResultData<Vacancies>> = flow {
        val searchResult = client.getVacancies(SearchRequest.setSearchOptions(text, page, updateSearchSettings()))
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

            error is HttpException -> {
                emit(SearchResultData.ErrorServer(R.string.server_error))
            }
        }
    }

    private fun updateSearchSettings(): SearchSettings {
        val settingsStr = settingsPref.getString(SEARCHING_OPTIONS, "")
        return json.fromJson(settingsStr, SearchSettings::class.java)
    }
}

package ru.practicum.android.diploma.domain.api

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.domain.models.SearchResultData
import ru.practicum.android.diploma.domain.models.vacancy.Vacancies

const val START_PAGE = 0

interface RepositoryVacavcies {
    suspend fun getVacancies(text: String, page: Int = START_PAGE): Flow<SearchResultData<Vacancies>>
}

package ru.practicum.android.diploma.domain.api

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.domain.models.SearchResultData
import ru.practicum.android.diploma.domain.models.vacancy.Vacancies

interface RepositoryVacavcies {
    suspend fun getVacancies(text: String): Flow<SearchResultData<Vacancies>>
}

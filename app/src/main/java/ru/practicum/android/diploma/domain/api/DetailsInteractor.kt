package ru.practicum.android.diploma.domain.api

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.domain.models.SearchResultData
import ru.practicum.android.diploma.domain.models.vacancy.VacancyDetails

interface DetailsInteractor {
    suspend fun getVacancyDetails(vacancyId: String): Flow<SearchResultData<VacancyDetails>>
}

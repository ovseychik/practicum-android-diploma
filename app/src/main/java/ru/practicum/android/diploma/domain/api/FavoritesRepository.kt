package ru.practicum.android.diploma.domain.api

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.domain.models.SearchResultData
import ru.practicum.android.diploma.domain.models.vacancy.VacancyDetails

interface FavoritesRepository {
    suspend fun addVacancyToFavorite(vacancyDetails: VacancyDetails)
    suspend fun deleteVacancyFromFavorite(vacancyDetails: VacancyDetails)
    suspend fun getVacancy(vacancyId: String): VacancyDetails
    suspend fun isVacancyFavorite(vacancyId: String): Boolean
    suspend fun getFavoriteVacancies(): Flow<SearchResultData<List<VacancyDetails>>>
}

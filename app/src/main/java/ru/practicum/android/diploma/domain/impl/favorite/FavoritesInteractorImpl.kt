package ru.practicum.android.diploma.domain.impl.favorite

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.domain.api.FavoritesInteractor
import ru.practicum.android.diploma.domain.api.FavoritesRepository
import ru.practicum.android.diploma.domain.models.vacancy.VacancyDetails
import ru.practicum.android.diploma.domain.models.vacancy.VacancyItem

class FavoritesInteractorImpl(
    private val favoritesRepository: FavoritesRepository
) : FavoritesInteractor {
    override suspend fun addVacancyToFavorite(vacancyDetails: VacancyDetails) {
        favoritesRepository.addVacancyToFavorite(vacancyDetails)
    }

    override suspend fun deleteVacancyFromFavorite(vacancyDetails: VacancyDetails) {
        favoritesRepository.deleteVacancyFromFavorite(vacancyDetails)
    }

    override suspend fun getVacancy(vacancyId: String): VacancyDetails? {
        return favoritesRepository.getVacancy(vacancyId)
    }

    override suspend fun isVacancyFavorite(vacancyId: String): Boolean {
        return favoritesRepository.isVacancyFavorite(vacancyId)
    }

    override suspend fun getFavoriteVacancies(): Flow<List<VacancyItem>> {
        return favoritesRepository.getFavoriteVacancies()
    }
}

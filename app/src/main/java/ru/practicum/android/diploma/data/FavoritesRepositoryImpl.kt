package ru.practicum.android.diploma.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.practicum.android.diploma.data.db.AppDatabase
import ru.practicum.android.diploma.data.db.convertors.FavoriteVacanciesConvertors
import ru.practicum.android.diploma.domain.api.FavoritesRepository
import ru.practicum.android.diploma.domain.models.vacancy.VacancyDetails
import ru.practicum.android.diploma.domain.models.vacancy.VacancyItem

class FavoritesRepositoryImpl(
    private val appDatabase: AppDatabase,
    private val vacancyConvertors: FavoriteVacanciesConvertors
) : FavoritesRepository {
    override suspend fun addVacancyToFavorite(vacancyDetails: VacancyDetails) {
        val vacancyEntity = vacancyConvertors.mapToEntity(vacancyDetails)
        appDatabase.vacancyDao().insertVacancy(vacancyEntity)
    }

    override suspend fun deleteVacancyFromFavorite(vacancyDetails: VacancyDetails) {
        val vacancyEntity = vacancyConvertors.mapToEntity(vacancyDetails)
        appDatabase.vacancyDao().deleteVacancy(vacancyEntity)
    }

    override suspend fun getVacancy(vacancyId: String): VacancyDetails? {
        val vacancyEntity = appDatabase.vacancyDao().getVacancy(vacancyId)
        return vacancyEntity?.let { vacancyConvertors.mapToVacancyDetails(it) }
    }

    override suspend fun isVacancyFavorite(vacancyId: String): Boolean {
        val vacancyEntity = appDatabase.vacancyDao().getVacancy(vacancyId)
        return appDatabase.vacancyDao().getVacancies().contains(vacancyEntity)
    }

    override suspend fun getFavoriteVacancies(): Flow<List<VacancyItem>> = flow {
        val favoriteVacancies = appDatabase.vacancyDao().getVacancies()
        val favoriteVacanciesList =
            favoriteVacancies.map { vacancyEntity -> vacancyConvertors.mapToVacansyItem(vacancyEntity) }
        emit(favoriteVacanciesList)
    }
}

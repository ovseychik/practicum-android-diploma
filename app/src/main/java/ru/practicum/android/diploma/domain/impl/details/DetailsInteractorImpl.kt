package ru.practicum.android.diploma.domain.impl.details

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.domain.api.DetailsInteractor
import ru.practicum.android.diploma.domain.api.RepositoryDetails
import ru.practicum.android.diploma.domain.models.SearchResultData
import ru.practicum.android.diploma.domain.models.vacancy.VacancyDetails

class DetailsInteractorImpl(private val detailsRepository: RepositoryDetails) : DetailsInteractor {
    override suspend fun getVacancyDetails(vacancyId: String): Flow<SearchResultData<VacancyDetails>> {
        return detailsRepository.getVacancyDetails(vacancyId)
    }
}

package ru.practicum.android.diploma.domain.impl.search

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.domain.api.RepositoryVacancies
import ru.practicum.android.diploma.domain.api.VacanciesInteractor
import ru.practicum.android.diploma.domain.models.SearchResultData
import ru.practicum.android.diploma.domain.models.vacancy.Vacancies

class VacanciesInteractorImpl(private val vacanciesRepository: RepositoryVacancies) : VacanciesInteractor {
    override suspend fun getVacancies(query: String, pageNumber: Int): Flow<SearchResultData<Vacancies>> {
        return vacanciesRepository.getVacancies(query, pageNumber)
    }
}

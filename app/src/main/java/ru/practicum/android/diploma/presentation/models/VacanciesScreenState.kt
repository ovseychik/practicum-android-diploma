package ru.practicum.android.diploma.presentation.models

import androidx.annotation.StringRes
import ru.practicum.android.diploma.domain.models.vacancy.Vacancies
import ru.practicum.android.diploma.domain.models.vacancy.VacancyItem

sealed interface VacanciesScreenState {
    object IsLoading : VacanciesScreenState
    data class Content(val foundItems: Int, val listVacancies: List<VacancyItem>) : VacanciesScreenState
    data class Error(@StringRes val message: Int) : VacanciesScreenState
    data class Empty(@StringRes val message: Int) : VacanciesScreenState
    data class NoInternet(@StringRes val message: Int) : VacanciesScreenState
}

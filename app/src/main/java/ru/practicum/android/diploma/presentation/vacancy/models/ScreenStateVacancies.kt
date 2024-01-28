package ru.practicum.android.diploma.presentation.vacancy.models

import androidx.annotation.StringRes
import ru.practicum.android.diploma.domain.models.vacancy.VacancyItem

sealed interface ScreenStateVacancies {
    object IsLoading : ScreenStateVacancies
    data class Content(val foundItems: Int, val listVacancies: List<VacancyItem>) : ScreenStateVacancies
    data class Error(@StringRes val message: Int) : ScreenStateVacancies
    data class Empty(@StringRes val message: Int) : ScreenStateVacancies
    data class NoInternet(@StringRes val message: Int) : ScreenStateVacancies
    object NextPageIsLoading : ScreenStateVacancies
    data class NextPageIsLoaded(val listVacancies: List<VacancyItem>) : ScreenStateVacancies
}

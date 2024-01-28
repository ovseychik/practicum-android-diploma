package ru.practicum.android.diploma.presentation.vacancy.models

import androidx.annotation.StringRes
import ru.practicum.android.diploma.domain.models.vacancy.VacancyDetails

sealed interface ScreenStateDetails {
    object IsLoading : ScreenStateDetails
    data class Content(val details: VacancyDetails) : ScreenStateDetails
    data class Error(@StringRes val message: Int) : ScreenStateDetails
    data class NoInternet(@StringRes val message: Int) : ScreenStateDetails
}

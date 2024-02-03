package ru.practicum.android.diploma.presentation.settings.models

import androidx.annotation.StringRes
import ru.practicum.android.diploma.domain.models.guides.Country
import ru.practicum.android.diploma.domain.models.guides.IndustryItem

sealed interface CountriesScreenState {
    data class Content(val countriesList: List<Country>) : CountriesScreenState
    data class Error(@StringRes val message: Int) : CountriesScreenState
    data class NoInternet(@StringRes val message: Int) : CountriesScreenState
    object Loading : CountriesScreenState
}

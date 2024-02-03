package ru.practicum.android.diploma.presentation.settings.models

import androidx.annotation.StringRes
import ru.practicum.android.diploma.domain.models.guides.IndustryItem

sealed interface IndustriesScreenState {
    data class Content(val industriesList: List<IndustryItem>, val selectedIndustryName: String) : IndustriesScreenState
    data class Error(@StringRes val message: Int) : IndustriesScreenState
    data class NoInternet(@StringRes val message: Int) : IndustriesScreenState
    object Empty : IndustriesScreenState
    object Loading : IndustriesScreenState
}

package ru.practicum.android.diploma.presentation.settings.models

import androidx.annotation.StringRes
import ru.practicum.android.diploma.domain.models.guides.PlaceItem

interface PlacesScreenState {
    data class Content(val industriesList: List<PlaceItem>) : PlacesScreenState
    data class Error(@StringRes val message: Int) : PlacesScreenState
    data class NoInternet(@StringRes val message: Int) : PlacesScreenState
    object Empty : PlacesScreenState
    object Loading : PlacesScreenState
}

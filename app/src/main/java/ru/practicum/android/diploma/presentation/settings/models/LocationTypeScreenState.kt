package ru.practicum.android.diploma.presentation.settings.models

import ru.practicum.android.diploma.domain.models.guides.Country
import ru.practicum.android.diploma.domain.models.guides.PlaceItem

sealed interface LocationTypeScreenState {
    data class Content(val place: PlaceItem, val country: Country) : LocationTypeScreenState
    object Empty : LocationTypeScreenState
}

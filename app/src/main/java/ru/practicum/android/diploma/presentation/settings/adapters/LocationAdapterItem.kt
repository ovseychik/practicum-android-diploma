package ru.practicum.android.diploma.presentation.settings.adapters

import ru.practicum.android.diploma.domain.models.guides.Country
import ru.practicum.android.diploma.domain.models.guides.PlaceItem

sealed class LocationAdapterItem {
    data class City(val place: PlaceItem) : LocationAdapterItem()
    data class CountryItem(val country: Country) : LocationAdapterItem()
}

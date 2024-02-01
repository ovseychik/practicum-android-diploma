package ru.practicum.android.diploma.domain.api.settings

import ru.practicum.android.diploma.domain.models.guides.Country
import ru.practicum.android.diploma.domain.models.guides.IndustryItem
import ru.practicum.android.diploma.domain.models.guides.PlaceItem
import ru.practicum.android.diploma.domain.models.settings.SearchSettings

interface SettingsRepository {
    fun getSettings(): SearchSettings
    fun saveSettings(settings: SearchSettings)
    fun getCountryFromSettings(): Country
    fun getPlaceFromSettings(): PlaceItem
    fun setCountryInSettings(country: Country)
    fun setPlaceInSettings(place: PlaceItem)
    fun getIndustryFromSettings(): IndustryItem
    fun setIndustryInSettings(industry: IndustryItem)
}

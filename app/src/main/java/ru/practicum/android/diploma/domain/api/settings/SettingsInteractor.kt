package ru.practicum.android.diploma.domain.api.settings

import ru.practicum.android.diploma.domain.models.guides.Country
import ru.practicum.android.diploma.domain.models.guides.IndustryItem
import ru.practicum.android.diploma.domain.models.guides.PlaceItem
import ru.practicum.android.diploma.domain.models.settings.SearchSettings

interface SettingsInteractor {
    fun getSettings(): SearchSettings
    fun saveSettings(settings: SearchSettings)
}

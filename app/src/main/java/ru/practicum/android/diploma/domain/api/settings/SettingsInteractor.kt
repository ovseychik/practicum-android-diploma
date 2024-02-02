package ru.practicum.android.diploma.domain.api.settings

import ru.practicum.android.diploma.domain.models.settings.SearchSettings

interface SettingsInteractor {
    fun getSettings(): SearchSettings
    fun saveSettings(settings: SearchSettings)
}

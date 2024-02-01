package ru.practicum.android.diploma.data

import ru.practicum.android.diploma.data.models.SearchSettingsData

interface SettingsStorage {
    fun getSettings(): SearchSettingsData
    fun saveSettings(settings: SearchSettingsData)
}

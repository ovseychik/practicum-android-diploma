package ru.practicum.android.diploma.domain.impl.settings

import ru.practicum.android.diploma.domain.api.settings.SettingsInteractor
import ru.practicum.android.diploma.domain.api.settings.SettingsRepository
import ru.practicum.android.diploma.domain.models.settings.SearchSettings

class SettingInteractorImpl(private val settingsRepository: SettingsRepository) : SettingsInteractor {
    override fun getSettings(): SearchSettings {
        return settingsRepository.getSettings()
    }

    override fun saveSettings(settings: SearchSettings) {
        settingsRepository.saveSettings(settings)
    }

}

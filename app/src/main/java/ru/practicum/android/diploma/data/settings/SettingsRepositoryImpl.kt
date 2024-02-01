package ru.practicum.android.diploma.data.settings

import ru.practicum.android.diploma.data.SettingsStorage
import ru.practicum.android.diploma.data.models.mapToSearchSetting
import ru.practicum.android.diploma.data.models.mapToSearchSettingsData
import ru.practicum.android.diploma.domain.api.settings.SettingsRepository
import ru.practicum.android.diploma.domain.models.guides.Country
import ru.practicum.android.diploma.domain.models.guides.IndustryItem
import ru.practicum.android.diploma.domain.models.guides.PlaceItem
import ru.practicum.android.diploma.domain.models.settings.SearchSettings

class SettingsRepositoryImpl(private val storage: SettingsStorage) : SettingsRepository {
    override fun getSettings(): SearchSettings {
        return mapToSearchSetting(storage.getSettings())
    }

    override fun saveSettings(settings: SearchSettings) {
        storage.saveSettings(mapToSearchSettingsData(settings))
    }

    override fun getCountryFromSettings(): Country {
        return mapToSearchSetting(storage.getSettings()).country
    }

    override fun getPlaceFromSettings(): PlaceItem {
        return mapToSearchSetting(storage.getSettings()).place
    }

    override fun getIndustryFromSettings(): IndustryItem {
        return mapToSearchSetting(storage.getSettings()).industry
    }

    override fun setCountryInSettings(country: Country) {
        val settings = mapToSearchSetting(storage.getSettings())
        storage.saveSettings(mapToSearchSettingsData(settings.copy(country = country)))
    }

    override fun setPlaceInSettings(place: PlaceItem) {
        val settings = mapToSearchSetting(storage.getSettings())
        storage.saveSettings(mapToSearchSettingsData(settings.copy(place = place)))
    }

    override fun setIndustryInSettings(industry: IndustryItem) {
        val settings = mapToSearchSetting(storage.getSettings())
        storage.saveSettings(mapToSearchSettingsData(settings.copy(industry = industry)))
    }

}

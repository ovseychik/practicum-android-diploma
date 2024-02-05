package ru.practicum.android.diploma.data.models

import ru.practicum.android.diploma.domain.models.guides.Country
import ru.practicum.android.diploma.domain.models.guides.IndustryItem
import ru.practicum.android.diploma.domain.models.guides.PlaceItem
import ru.practicum.android.diploma.domain.models.settings.SearchSettings

const val EMPTY_PARAM_NUM = -101
const val EMPTY_PARAM_SRT = ""

data class SearchSettingsData(
    val settingsId: ValuesSearchId = ValuesSearchId.BASE,
    val salary: Int = EMPTY_PARAM_NUM,
    val isSalarySpecified: Boolean = false,
    val countryId: String = EMPTY_PARAM_SRT,
    val placeId: String = EMPTY_PARAM_SRT,
    val countryName: String = EMPTY_PARAM_SRT,
    val placeName: String = EMPTY_PARAM_SRT,
    val industryName: String = EMPTY_PARAM_SRT,
    val industryId: String = EMPTY_PARAM_SRT
)

fun mapToSearchSetting(settings: SearchSettingsData): SearchSettings {
    return SearchSettings(
        settingsId = settings.settingsId,
        salary = settings.salary,
        isSalarySpecified = settings.isSalarySpecified,
        country = Country(settings.countryName, settings.countryId),
        industry = IndustryItem(industryName = settings.industryName, industryId = settings.industryId),
        place = PlaceItem(settings.placeName, settings.placeId)
    )
}

fun mapToSearchSettingsData(settings: SearchSettings): SearchSettingsData {
    return SearchSettingsData(
        settingsId = settings.settingsId,
        salary = settings.salary,
        isSalarySpecified = settings.isSalarySpecified,
        placeId = settings.place.areaId,
        placeName = settings.place.areaName,
        countryId = settings.country.countryId,
        countryName = settings.country.countryName,
        industryName = settings.industry.industryName,
        industryId = settings.industry.industryId
    )
}

enum class ValuesSearchId {
    MODIFIED,
    BASE
}

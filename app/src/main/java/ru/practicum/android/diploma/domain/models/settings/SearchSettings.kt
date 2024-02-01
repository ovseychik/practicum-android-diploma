package ru.practicum.android.diploma.domain.models.settings

import ru.practicum.android.diploma.data.dto.responses.guides.inustries.Industry
import ru.practicum.android.diploma.data.models.EMPTY_PARAM_NUM
import ru.practicum.android.diploma.data.models.ValuesSearchId
import ru.practicum.android.diploma.domain.models.guides.Country
import ru.practicum.android.diploma.domain.models.guides.IndustryItem
import ru.practicum.android.diploma.domain.models.guides.PlaceItem

data class SearchSettings (
    val settingsId: ValuesSearchId = ValuesSearchId.BASE, // id настроек для определения способа выхода с экрана настроек
    val salary: Int = EMPTY_PARAM_NUM,
    val isSalarySpecified: Boolean = false,
    val country: Country,
    val place: PlaceItem,
    val industry: IndustryItem
)

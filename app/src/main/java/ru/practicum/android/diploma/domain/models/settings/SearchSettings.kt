package ru.practicum.android.diploma.domain.models.settings

import ru.practicum.android.diploma.data.models.EMPTY_PARAM_NUM
import ru.practicum.android.diploma.data.models.ValuesSearchId
import ru.practicum.android.diploma.domain.models.guides.Country
import ru.practicum.android.diploma.domain.models.guides.IndustryItem
import ru.practicum.android.diploma.domain.models.guides.PlaceItem

data class SearchSettings(
    val settingsId: ValuesSearchId = ValuesSearchId.BASE,
    val salary: Int = EMPTY_PARAM_NUM,
    val isSalarySpecified: Boolean = false,
    val country: Country,
    val place: PlaceItem,
    val industry: IndustryItem
)

fun setDefault(): SearchSettings {
    return SearchSettings(
        settingsId = ValuesSearchId.BASE,
        salary = EMPTY_PARAM_NUM,
        isSalarySpecified = false,
        country = Country("", ""),
        place = PlaceItem("", ""),
        industry = IndustryItem(""),
    )
}

package ru.practicum.android.diploma.data.dto

import ru.practicum.android.diploma.data.models.EMPTY_PARAM_NUM
import ru.practicum.android.diploma.data.models.EMPTY_PARAM_SRT
import ru.practicum.android.diploma.data.models.SearchSettings

data class SearchRequest(
    val searchOptionsStr: Map<String, String>,
    val searchOptionsNum: Map<String, Int>,
    val onlyWithSalary: Boolean,
) {
    companion object {
        private const val KEY_TEXT = "text"
        private const val KEY_AREA = "area"
        private const val KEY_INDUSTRY = "industry"
        private const val ITEMS_PER_SHEET = 20
        private const val KEY_PAGE = "page"
        private const val KEY_PER_PAGE = "per_page"
        private const val KEY_SALARY = "salary"

        fun setSearchOptions(
            text: String = "",
            page: Int = 0,
            searchSettings: SearchSettings
        ): SearchRequest {
            val mapStr = mutableMapOf<String, String>()
            if (text != "") mapStr[KEY_TEXT] = text
            if (searchSettings.areaId != EMPTY_PARAM_SRT) mapStr[KEY_AREA] = searchSettings.areaId
            if (searchSettings.industry != EMPTY_PARAM_SRT) mapStr[KEY_INDUSTRY] = searchSettings.industry
            val mapNum = mutableMapOf<String, Int>()
            mapNum[KEY_PER_PAGE] = ITEMS_PER_SHEET
            if (page >= 0) mapNum[KEY_PAGE] = page
            if (searchSettings.salary != EMPTY_PARAM_NUM) mapNum[KEY_SALARY] = searchSettings.salary
            return SearchRequest(mapStr, mapNum, searchSettings.isSalarySpecified)
        }
    }
}

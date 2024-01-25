package ru.practicum.android.diploma.data.dto

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
            text: String,
            page: Int,
            searchSettings: SearchSettings
        ): SearchRequest {
            val mapStr = mapOf<String, String>(
                KEY_TEXT to text,
                KEY_AREA to searchSettings.areaId,
                KEY_INDUSTRY to searchSettings.industry
            )
            val mapNum = mapOf<String, Int>(
                KEY_PAGE to page,
                KEY_PER_PAGE to ITEMS_PER_SHEET,
                KEY_SALARY to searchSettings.salary
            )
            return SearchRequest(mapStr, mapNum, searchSettings.isSalarySpecified)
        }
    }
}

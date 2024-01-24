package ru.practicum.android.diploma.data.dto

data class SearchRequest(
    val searchOptions: Map<String, String> = mutableMapOf(
        KEY_TEXT to "",
        KEY_AREA to "",
        KEY_INDUSTRY to "",
    ),
    val onlyWithSalary: Boolean = false,
    val page: Int,
    val perPage:Int = ITEMS_PER_SHEET,
    val salary: Int
) {
    companion object {
        private const val KEY_TEXT = "text"
        private const val KEY_AREA = "area"
        private const val KEY_INDUSTRY = "industry"
        private const val ITEMS_PER_SHEET = 20

        fun updatingSettings(
            text: String,
            page: Int,
            area: String,
            industry: String,
            salary: Int,
            isSalaryIndicate: Boolean
        ): SearchRequest {
            val params = mutableMapOf<String, String>()
            if (text != "") params[KEY_TEXT] = text
            if (area != "") params[KEY_AREA] = area
            if (industry !="") params[KEY_INDUSTRY] = industry
            return SearchRequest(
                searchOptions = params,
                onlyWithSalary = isSalaryIndicate,
                page = page,
                perPage = 20,
                salary = salary,

            )
        }

    }
}

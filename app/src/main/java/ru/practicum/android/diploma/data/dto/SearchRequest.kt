package ru.practicum.android.diploma.data.dto

data class SearchRequest(
    val searchOptions: Map<String, String> = mutableMapOf(
        KEY_TEXT to "",
        KEY_AREA to "",
        KEY_INDUSTRY to "",
    ),
    val onlyWithSalary: Boolean = false,
    val page: Int,
    val perPage: Int = ITEMS_PER_SHEET,
    val salary: Int
) {
    companion object {
        const val KEY_TEXT = "text"
        const val KEY_AREA = "area"
        const val KEY_INDUSTRY = "industry"
        const val ITEMS_PER_SHEET = 20
    }
}

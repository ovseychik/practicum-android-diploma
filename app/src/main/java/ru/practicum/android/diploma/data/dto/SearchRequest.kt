package ru.practicum.android.diploma.data.dto

data class SearchRequest(
    val searchOptions: MutableMap<String, String> = mutableMapOf(
        KEY_PAGE to "",
        KEY_PER_PAGE to ITEMS_PER_SHEET,
        KEY_TEXT to "",
        KEY_AREA to "",
        KEY_INDUSTRY to ""
    ),
    val vacancyId: String = ""
) {
    companion object {
        const val KEY_PAGE = "page"
        const val KEY_PER_PAGE = "per_page"
        const val KEY_TEXT = "text"
        const val KEY_AREA = "area"
        const val KEY_INDUSTRY = "industry"
        const val ITEMS_PER_SHEET = "20"
    }
}

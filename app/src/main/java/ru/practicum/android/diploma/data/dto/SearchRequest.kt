package ru.practicum.android.diploma.data.dto

const val HH_USER_AGENT: String = "HH-User-Agent: Get a job! (morozov@rtr.spb.ru)"

const val KEY_PAGE = "page"
const val KEY_PER_PAGE = "per_page"
const val KEY_TEXT = "text"
const val KEY_AREA = "area"
const val KEY_INDUSTRY = "industry"
const val PAGES_PER_SHEET = "20"

data class SearchRequest(
    val searchOptions: MutableMap<String, String> = mutableMapOf(
        KEY_PAGE to "",
        KEY_PER_PAGE to PAGES_PER_SHEET,
        KEY_TEXT to "",
        KEY_AREA to "",
        KEY_INDUSTRY to ""
    ),
    val vacancyId: String = ""
)

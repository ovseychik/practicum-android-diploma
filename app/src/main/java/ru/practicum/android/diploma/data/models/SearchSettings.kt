package ru.practicum.android.diploma.data.models

const val EMPTY_PARAM_NUM = -101
const val EMPTY_PARAM_SRT = ""

data class SearchSettings(
    val settingsId: ValuesSearchId, // id настроек для определения способа выхода с экрана настроек
    val salary: Int = EMPTY_PARAM_NUM,
    val isSalarySpecified: Boolean = false,
    val areaId: String = EMPTY_PARAM_SRT,
    val industry: String = EMPTY_PARAM_SRT
)

enum class ValuesSearchId {
    MODIFIED,
    BASE
}

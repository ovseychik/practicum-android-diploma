package ru.practicum.android.diploma.data.models

const val EMPTY_PARAM_NUM = -1
const val EMPTY_PARAM_SRT = ""

data class SearchSettings(
    val salary: Int = EMPTY_PARAM_NUM,
    val isSalarySpecified: Boolean = false,
    val areaId: String = EMPTY_PARAM_SRT,
    val industry: String = EMPTY_PARAM_SRT
)

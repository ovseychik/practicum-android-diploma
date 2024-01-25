package ru.practicum.android.diploma.data.models

const val EMPTY_PARAM_NUM = -1
const val EMPTY_PARAM_SRT = ""

data class SearchSettings(
    var salary: Int = EMPTY_PARAM_NUM,
    var isSalarySpecified: Boolean = false,
    var areaId: String = EMPTY_PARAM_SRT,
    var industry: String = EMPTY_PARAM_SRT
)

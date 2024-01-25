package ru.practicum.android.diploma.data.models

data class SearchSettings(
    val salary: Int,
    val isSalarySpecified: Boolean,
    val areaId: String,
    val industry: String
)

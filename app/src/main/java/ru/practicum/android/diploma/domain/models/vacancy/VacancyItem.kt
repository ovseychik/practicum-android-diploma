package ru.practicum.android.diploma.domain.models.vacancy

data class VacancyItem(
    val id: String,
    val city: String,
    val logo: String,
    val nameVacancy: String,
    val nameCompany: String,
    val salary: String
)

package ru.practicum.android.diploma.domain.models.vacancy

data class Vacancies(
    val foundItems: Int,
    val page: Int,
    val listVacancies: List<VacancyItem>
)

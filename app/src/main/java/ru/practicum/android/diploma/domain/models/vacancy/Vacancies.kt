package ru.practicum.android.diploma.domain.models.vacancy

data class Vacancies(
    val page: Int,
    val listVacancies: List<VacancyItem>
)

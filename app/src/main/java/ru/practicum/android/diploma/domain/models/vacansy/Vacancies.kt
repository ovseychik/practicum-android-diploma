package ru.practicum.android.diploma.domain.models.vacansy

data class Vacancies(
    val page: Int,
    val listVacancies: List<VacancyItem>
)

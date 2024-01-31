package ru.practicum.android.diploma.presentation.vacancy

import ru.practicum.android.diploma.domain.models.vacancy.VacancyItem

sealed class ItemType {
    data class Vacancy(val vacancyItem: VacancyItem) : ItemType()
    object Loading : ItemType()
}

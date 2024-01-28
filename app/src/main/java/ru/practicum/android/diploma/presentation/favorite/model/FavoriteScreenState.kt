package ru.practicum.android.diploma.presentation.favorite.model

import ru.practicum.android.diploma.domain.models.vacancy.VacancyItem

sealed interface FavoriteScreenState {
    data class Content(val vacancies: List<VacancyItem>) : FavoriteScreenState
    object Empty : FavoriteScreenState
    object Error : FavoriteScreenState
}

package ru.practicum.android.diploma.presentation.favorite.model

import ru.practicum.android.diploma.domain.models.vacancy.VacancyItem

sealed class FavoriteScreenState {
    data class Content(val vacancies: List<VacancyItem>) : FavoriteScreenState()
    data class Empty(val messageRes: Int) : FavoriteScreenState()
    data class Error(val messageRes: Int) : FavoriteScreenState()
}

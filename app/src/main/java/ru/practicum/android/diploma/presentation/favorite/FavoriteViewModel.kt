package ru.practicum.android.diploma.presentation.favorite

import androidx.lifecycle.ViewModel
import ru.practicum.android.diploma.domain.api.FavoritesInteractor

class FavoriteViewModel(
    private val favoritesInteractor: FavoritesInteractor
) : ViewModel() {
}

package ru.practicum.android.diploma.presentation.favorite.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.domain.api.FavoritesInteractor
import ru.practicum.android.diploma.domain.models.vacancy.VacancyItem
import ru.practicum.android.diploma.presentation.favorite.model.FavoriteScreenState

class FavoriteViewModel(
    private val favoritesInteractor: FavoritesInteractor
) : ViewModel() {
    private val _favoriteStateLiveData = MutableLiveData<FavoriteScreenState>()

    fun observeState(): LiveData<FavoriteScreenState> = _favoriteStateLiveData

    fun fillData() {
        viewModelScope.launch {
            favoritesInteractor
                .getFavoriteVacancies()
                .collect { vacancies ->
                    processResult(vacancies)
                }
        }
    }

    private fun processResult(vacancies: List<VacancyItem>) {
        if (vacancies.isNullOrEmpty()) {
            renderState(FavoriteScreenState.Empty)
        } else if (vacancies.isNotEmpty()) {
            renderState(FavoriteScreenState.Content(vacancies))
        } else {
            renderState(FavoriteScreenState.Error)
        }
    }

    private fun renderState(state: FavoriteScreenState) {
        _favoriteStateLiveData.postValue(state)
    }
}

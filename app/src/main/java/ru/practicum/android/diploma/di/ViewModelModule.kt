package ru.practicum.android.diploma.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.practicum.android.diploma.presentation.favorite.FavoriteViewModel
import ru.practicum.android.diploma.presentation.vacancy.viewmodel.SearhViewModel

val viewModelModule = module {
    viewModel {
        SearhViewModel(vacanciesInteractor = get())
    }

    viewModel {
        FavoriteViewModel(favoritesInteractor = get())
    }
}

package ru.practicum.android.diploma.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.practicum.android.diploma.presentation.view_model.SearhViewModel

val viewModelModule = module {
    viewModel {
        SearhViewModel(vacanciesInteractor = get())
    }
}

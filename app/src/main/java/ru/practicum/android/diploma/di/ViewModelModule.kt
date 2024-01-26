package ru.practicum.android.diploma.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.practicum.android.diploma.presentation.MyViewModel

val viewModelModule = module {
    viewModel { MyViewModel(repositoryDetails = get(), repositoryVacancies = get()) }
}

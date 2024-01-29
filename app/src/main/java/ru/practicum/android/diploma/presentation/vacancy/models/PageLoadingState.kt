package ru.practicum.android.diploma.presentation.vacancy.models

sealed interface PageLoadingState {
    object ServerError : PageLoadingState
    object InternetError : PageLoadingState
}

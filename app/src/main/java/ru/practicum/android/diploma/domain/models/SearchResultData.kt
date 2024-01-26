package ru.practicum.android.diploma.domain.models

sealed interface SearchResultData<T> {
    data class Data<T>(val value: T) : SearchResultData<T>
    data class ErrorServer<T>(val message: String) : SearchResultData<T>
    data class NoInternet<T>(val message: String) : SearchResultData<T>
    data class Empty<T>(val message: String) : SearchResultData<T>
}

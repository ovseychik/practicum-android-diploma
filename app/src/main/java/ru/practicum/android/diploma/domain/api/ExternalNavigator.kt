package ru.practicum.android.diploma.domain.api

interface ExternalNavigator {
    fun shareLink(url: String?)
    fun openEmail(email: String?): Result<String>
    fun openDial(number: String?)
}

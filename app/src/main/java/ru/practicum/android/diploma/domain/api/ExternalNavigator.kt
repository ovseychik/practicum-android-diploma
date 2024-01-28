package ru.practicum.android.diploma.domain.api

interface ExternalNavigator {
    fun shareLink(url: String?)
    fun openEmail(email: String?)
    fun openDial(number: String?)
}

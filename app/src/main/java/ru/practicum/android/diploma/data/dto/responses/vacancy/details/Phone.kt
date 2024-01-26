package ru.practicum.android.diploma.data.dto.responses.vacancy.details

import ru.practicum.android.diploma.data.models.EMPTY_PARAM_SRT

data class Phone(
    val city: String, // код города
    val comment: String? = EMPTY_PARAM_SRT, // комментарий
    val country: String, // код страны
    val number: String // номер телефона
)

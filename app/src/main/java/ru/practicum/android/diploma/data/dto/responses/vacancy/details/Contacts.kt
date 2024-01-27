package ru.practicum.android.diploma.data.dto.responses.vacancy.details

import ru.practicum.android.diploma.data.models.EMPTY_PARAM_SRT

data class Contacts(
    val email: String = EMPTY_PARAM_SRT,
    val name: String = EMPTY_PARAM_SRT, // имя менеджера
    val phones: List<Phone> = emptyList() // список телефонов
)

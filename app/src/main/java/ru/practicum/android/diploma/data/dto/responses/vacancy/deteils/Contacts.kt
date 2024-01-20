package ru.practicum.android.diploma.data.dto.responses.vacancy.deteils

data class Contacts(
    val email: String,
    val name: String, // имя менеджера
    val phones: List<Phone> // список телефонов
)

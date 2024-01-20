package com.example.myapplication.models.current_vacancy

data class Contacts(
    val email: String,
    val name: String, // имя менеджера
    val phones: List<Phone> // список телефонов
)
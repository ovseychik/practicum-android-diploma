package com.example.myapplication.models.list_vacancy

data class Salary(
    val currency: String, // валюта код
    val from: Int, // нижняя граница з/п
    val gross: Boolean, // признак что до вычета налогов
    val to: Any // верхняя граница з/п
)
package com.example.myapplication.models.current_vacancy

data class Salary(
    val currency: String, // валюта обозначение на латинице
    val from: Int, // нижний порог з/п
    val gross: Boolean, // з/п указана довычета налогов ??
    val to: Int? // перхний порог з/п может не быть
)
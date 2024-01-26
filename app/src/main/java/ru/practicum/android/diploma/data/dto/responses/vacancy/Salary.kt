package ru.practicum.android.diploma.data.dto.responses.vacancy

import ru.practicum.android.diploma.data.models.EMPTY_PARAM_NUM

data class Salary(
    val currency: String, // валюта обозначение на латинице
    val from: Int, // нижний порог з/п
    val gross: Boolean, // з/п указана довычета налогов ??
    val to: Int? = EMPTY_PARAM_NUM // перхний порог з/п может не быть
)

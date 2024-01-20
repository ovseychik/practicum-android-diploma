package ru.practicum.android.diploma.data.dto.responses.vacancy

import ru.practicum.android.diploma.data.dto.responses.vacancy.list.LogoUrls

data class Employer(
    val id: String,
    val logo_urls: LogoUrls, // внутри логотипы разных размеров
    val name: String, // название компании
    val trusted: Boolean, // аккредитация (может дальше по заданию пригодиться???)
)

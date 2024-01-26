package ru.practicum.android.diploma.data.dto.responses.vacancy

import com.google.gson.annotations.SerializedName
import ru.practicum.android.diploma.data.dto.responses.vacancy.list.LogoUrls

data class Employer(
    val id: String,
    @SerializedName("logo_urls")
    val logoUrls: LogoUrls?, // внутри логотипы разных размеров
    val name: String, // название компании
    val trusted: Boolean, // аккредитация (может дальше по заданию пригодиться???)
)

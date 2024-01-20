package com.example.myapplication.models.current_vacancy

data class Employer(
    val id: String,
    val logo_urls: LogoUrls, // логотипы разных размеров компании
    val name: String, // название компании
    val trusted: Boolean, // прошлали компания проверку на сайте??
)
package ru.practicum.android.diploma.data.dto.responses.vacancy.list

import com.google.gson.annotations.SerializedName

data class LogoUrls(
    @SerializedName("`240`") val medium: String,
    @SerializedName("`90`") val little: String,
    val original: String
)

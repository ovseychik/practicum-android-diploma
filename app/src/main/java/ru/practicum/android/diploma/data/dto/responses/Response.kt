package ru.practicum.android.diploma.data.dto.responses

import retrofit2.HttpException

open class Response {
    var resultCode = 0
    var error: HttpException? = null
}

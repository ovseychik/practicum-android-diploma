package ru.practicum.android.diploma.data.dto.responses

import retrofit2.HttpException

open class ResponseGuide<T> {
    var resultCode = 0
    var listItem = arrayListOf<T>()
    var error: HttpException? = null
}

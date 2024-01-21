package ru.practicum.android.diploma.data.dto.responses

open class ResponseGuide<T> {
    var resultCode = 0
    var listItem = arrayListOf<T>()
    var errorCode = 0
}

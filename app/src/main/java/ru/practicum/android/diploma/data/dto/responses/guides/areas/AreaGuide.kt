package ru.practicum.android.diploma.data.dto.responses.guides.areas

data class AreaGuide(
    val areas: List<AreaGuide>?,
    val id: String,
    val name: String,
    val parent_id: String
)

package ru.practicum.android.diploma.data.dto.responses.guides.areas

data class ResponseAreasGuideItem(
    val areas: List<AreaGuide>, // список локаций
    val id: String,
    val name: String, // название локации
    val parent_id: Any // id родительской локации
)

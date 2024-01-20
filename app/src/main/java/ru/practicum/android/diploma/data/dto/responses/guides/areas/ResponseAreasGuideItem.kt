package ru.practicum.android.diploma.data.dto.responses.guides.areas

import com.google.gson.annotations.SerializedName

data class ResponseAreasGuideItem(
    val areas: List<AreaGuide>, // список локаций
    val id: String,
    val name: String, // название локации
    @SerializedName("parent_id")
    val parentId: String? // id родительской локации
)

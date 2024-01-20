package com.example.myapplication.models.model_guide_area

data class ResponseAreasGuideItem(
    val areas: List<Area>, // список локаций
    val id: String,
    val name: String, // название локации
    val parent_id: Any // id родительской локации
)
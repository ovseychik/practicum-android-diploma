package com.example.myapplication.models.model_guide_area

data class Area(
    val areas: List<Area>?,
    val id: String,
    val name: String,
    val parent_id: String
)
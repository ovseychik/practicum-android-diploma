package com.example.myapplication.models.model_guide_inustries

data class ResponseGuideIndustriesDtoItem(
    val id: String,
    val industries: List<Industry>, // список подиндустрий
    val name: String // название индустрии
)
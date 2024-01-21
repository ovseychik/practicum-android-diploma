package ru.practicum.android.diploma.data.dto.responses.guides.inustries

data class ResponseGuideIndustriesDtoItem(
    val id: String,
    val industries: List<Industry>, // список подиндустрий
    val name: String // название индустрии
)

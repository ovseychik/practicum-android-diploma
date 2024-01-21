package ru.practicum.android.diploma.data.dto.responses.guides.areas

import ru.practicum.android.diploma.data.dto.responses.Response

data class ResponseAreaGuideDto(
    val areas: List<AreaGuide>,
    val id: String,
    val name: String,
    val parent_id: Any
) : Response()

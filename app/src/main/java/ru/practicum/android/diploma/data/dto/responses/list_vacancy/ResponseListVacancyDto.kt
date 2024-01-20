package com.example.myapplication.models.list_vacancy

import ru.practicum.android.diploma.data.dto.responses.Response

data class ResponseListVacancyDto(
    val found: Int,  // всего найдено по запросу
    val items: List<Item>, // список вакансий
    val page: Int, // номер страницы
    val pages: Int, // всего страниц
    val per_page: Int, // кол-во итемов на странице
) : Response()

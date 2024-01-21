package ru.practicum.android.diploma.data

import ru.practicum.android.diploma.data.dto.GuideRequest
import ru.practicum.android.diploma.data.dto.SearchRequest
import ru.practicum.android.diploma.data.dto.responses.Response
import ru.practicum.android.diploma.data.dto.responses.ResponseGuide
import ru.practicum.android.diploma.data.dto.responses.guides.countries.ResponseCountriesGuideItem
import ru.practicum.android.diploma.data.dto.responses.guides.inustries.ResponseIndustriesGuideItem

interface NetworkClient {
    suspend fun getVacancies(request: SearchRequest): Response?

    suspend fun getCurrentVacancy(request: SearchRequest): Response?

    suspend fun getSimilarVacancies(request: SearchRequest): Response?

    suspend fun getIndustries(): ResponseGuide<ResponseIndustriesGuideItem>?

    suspend fun getAreas(request: GuideRequest): Response?

    suspend fun getCountries(): ResponseGuide<ResponseCountriesGuideItem>

}

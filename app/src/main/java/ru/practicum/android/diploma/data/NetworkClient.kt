package ru.practicum.android.diploma.data

import ru.practicum.android.diploma.data.dto.SearchRequest
import ru.practicum.android.diploma.data.dto.responses.Response

interface NetworkClient {
    suspend fun getVacancies(request: SearchRequest): Response?

    suspend fun getCurrentVacancy(request: SearchRequest): Response?

    suspend fun getSimilarVacancies(request: SearchRequest): Response?

    suspend fun getIndustries(): Response?

    suspend fun getAreas(): Response?

}

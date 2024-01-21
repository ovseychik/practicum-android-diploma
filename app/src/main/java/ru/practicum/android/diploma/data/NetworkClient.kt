package ru.practicum.android.diploma.data

import ru.practicum.android.diploma.data.dto.responses.Response

interface NetworkClient {
    suspend fun getVacancies(searchOptions: MutableMap<String, String>): Response?

    suspend fun getCurrentVacancy(vacancyId: String): Response?

    suspend fun getSimilarVacancies(vacancyId: String, searchOptions: MutableMap<String, String>): Response?

    suspend fun getIndustries(): Response?

    suspend fun getAreas(): Response?

}

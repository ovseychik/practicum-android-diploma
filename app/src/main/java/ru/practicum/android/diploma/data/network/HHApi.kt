package ru.practicum.android.diploma.data.network

import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.QueryMap
import ru.practicum.android.diploma.BuildConfig
import ru.practicum.android.diploma.data.dto.HH_USER_AGENT
import ru.practicum.android.diploma.data.dto.responses.guides.areas.ResponseAreasGuide
import ru.practicum.android.diploma.data.dto.responses.vacancy.details.ResponseDetailsDto
import ru.practicum.android.diploma.data.dto.responses.vacancy.list.ResponseListDto

interface HHApi {
    @Headers(
        "Authorization: Bearer ${BuildConfig.HH_ACCESS_TOKEN}",
        HH_USER_AGENT
    )
    @GET("/vacancies")
    suspend fun getVacancies(@QueryMap searchOptions: MutableMap<String, String>): ResponseListDto

    @Headers(
        "Authorization: Bearer ${BuildConfig.HH_ACCESS_TOKEN}",
        HH_USER_AGENT
    )
    @GET("/vacancies/{vacancy_id}")
    suspend fun getVacancy(@Path("vacancy_id") id: String): ResponseDetailsDto

    @Headers(
        "Authorization: Bearer ${BuildConfig.HH_ACCESS_TOKEN}",
        HH_USER_AGENT
    )
    @GET("/vacancies/{vacancy_id}/similar_vacancies")
    suspend fun getSimilarVacancies(
        @Path("vacancy_id") id: String,
        @QueryMap options: MutableMap<String, String>
    ): ResponseListDto

    @Headers(
        "Authorization: Bearer ${BuildConfig.HH_ACCESS_TOKEN}",
        HH_USER_AGENT
    )
    @GET("/areas")
    suspend fun getAreas(): ResponseAreasGuide

    @Headers(
        "Authorization: Bearer ${BuildConfig.HH_ACCESS_TOKEN}",
        HH_USER_AGENT
    )
    @GET("/industries")
    suspend fun getIndustries(): ResponseAreasGuide
}

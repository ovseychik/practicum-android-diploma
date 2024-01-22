package ru.practicum.android.diploma.data.network

import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.QueryMap
import ru.practicum.android.diploma.BuildConfig
import ru.practicum.android.diploma.data.dto.responses.guides.areas.ResponseAreaGuideDto
import ru.practicum.android.diploma.data.dto.responses.guides.countries.ResponseCountriesGuideItem
import ru.practicum.android.diploma.data.dto.responses.guides.inustries.ResponseIndustriesGuideItem
import ru.practicum.android.diploma.data.dto.responses.vacancy.details.ResponseDetailsDto
import ru.practicum.android.diploma.data.dto.responses.vacancy.list.ResponseListDto
import ru.practicum.android.diploma.util.GlobalConstant

interface HHApi {
    @Headers(
        "Authorization: Bearer ${BuildConfig.HH_ACCESS_TOKEN}",
        GlobalConstant.HH_USER_AGENT
    )
    @GET("/vacancies")
    suspend fun getVacancies(@QueryMap searchOptions: MutableMap<String, String>): ResponseListDto

    @Headers(
        "Authorization: Bearer ${BuildConfig.HH_ACCESS_TOKEN}",
        GlobalConstant.HH_USER_AGENT
    )
    @GET("/vacancies/{vacancy_id}")
    suspend fun getVacancy(@Path("vacancy_id") id: String): ResponseDetailsDto

    @Headers(
        "Authorization: Bearer ${BuildConfig.HH_ACCESS_TOKEN}",
        GlobalConstant.HH_USER_AGENT
    )
    @GET("/vacancies/{vacancy_id}/similar_vacancies")
    suspend fun getSimilarVacancies(
        @Path("vacancy_id") id: String,
        @QueryMap options: MutableMap<String, String>
    ): ResponseListDto

    @Headers(
        "Authorization: Bearer ${BuildConfig.HH_ACCESS_TOKEN}",
        GlobalConstant.HH_USER_AGENT
    )
    @GET("/areas/countries")
    suspend fun getCountries(): ArrayList<ResponseCountriesGuideItem>

    @GET("/areas/{area_id}")
    suspend fun getAreas(@Path("area_id") id: String): ResponseAreaGuideDto

    @Headers(
        "Authorization: Bearer ${BuildConfig.HH_ACCESS_TOKEN}",
        GlobalConstant.HH_USER_AGENT
    )
    @GET("/industries")
    suspend fun getIndustries(): ArrayList<ResponseIndustriesGuideItem>
}

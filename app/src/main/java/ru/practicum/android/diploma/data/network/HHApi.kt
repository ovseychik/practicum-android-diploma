package ru.practicum.android.diploma.data.network

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap
import ru.practicum.android.diploma.data.dto.responses.guides.areas.ResponseAreaGuideDto
import ru.practicum.android.diploma.data.dto.responses.guides.countries.ResponseCountriesGuideItem
import ru.practicum.android.diploma.data.dto.responses.guides.industries.ResponseIndustriesGuideItem
import ru.practicum.android.diploma.data.dto.responses.vacancy.details.ResponseDetailsDto
import ru.practicum.android.diploma.data.dto.responses.vacancy.list.ResponseListDto


interface HHApi {
    @GET("/vacancies")
    suspend fun getVacancies(
        @QueryMap searchOptionsStr: Map<String, String>,
        @Query("only_with_salary") isSalarySpecified: Boolean,
        @QueryMap searchOptionsNum: Map<String, Int>
    ): ResponseListDto

    @GET("/vacancies/{vacancy_id}")
    suspend fun getVacancy(@Path("vacancy_id") id: String): ResponseDetailsDto

    @GET("/areas/countries")
    suspend fun getCountries(): List<ResponseCountriesGuideItem>

    @GET("/areas")
    suspend fun getAllAreas(): List<ResponseAreaGuideDto>

    @GET("/areas/{area_id}")
    suspend fun getAreas(@Path("area_id") id: String): ResponseAreaGuideDto

    @GET("/industries")
    suspend fun getIndustries(): List<ResponseIndustriesGuideItem>
}

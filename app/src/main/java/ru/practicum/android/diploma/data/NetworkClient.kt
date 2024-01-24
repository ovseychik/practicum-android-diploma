package ru.practicum.android.diploma.data

import ru.practicum.android.diploma.data.dto.DetailsRequest
import ru.practicum.android.diploma.data.dto.GuideRequest
import ru.practicum.android.diploma.data.dto.SearchRequest
import ru.practicum.android.diploma.data.dto.responses.guides.areas.ResponseAreaGuideDto
import ru.practicum.android.diploma.data.dto.responses.guides.countries.ResponseCountriesGuideItem
import ru.practicum.android.diploma.data.dto.responses.guides.inustries.ResponseIndustriesGuideItem
import ru.practicum.android.diploma.data.dto.responses.vacancy.details.ResponseDetailsDto
import ru.practicum.android.diploma.data.dto.responses.vacancy.list.ResponseListDto

interface NetworkClient {
    suspend fun getVacancies(request: SearchRequest): Result<ResponseListDto>

    suspend fun getCurrentVacancy(request: DetailsRequest): Result<ResponseDetailsDto>

    suspend fun getIndustries(): Result<List<ResponseIndustriesGuideItem>>?

    suspend fun getAreas(request: GuideRequest): Result<ResponseAreaGuideDto>

    suspend fun getAllAreas(): Result<List<ResponseAreaGuideDto>>

    suspend fun getCountries(): Result<List<ResponseCountriesGuideItem>>?

}

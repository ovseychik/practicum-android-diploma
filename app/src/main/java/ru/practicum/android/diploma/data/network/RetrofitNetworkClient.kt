package ru.practicum.android.diploma.data.network

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import ru.practicum.android.diploma.data.NetworkClient
import ru.practicum.android.diploma.data.dto.DetailsRequest
import ru.practicum.android.diploma.data.dto.GuideRequest
import ru.practicum.android.diploma.data.dto.SearchRequest
import ru.practicum.android.diploma.data.dto.responses.guides.areas.ResponseAreaGuideDto
import ru.practicum.android.diploma.data.dto.responses.guides.countries.ResponseCountriesGuideItem
import ru.practicum.android.diploma.data.dto.responses.guides.inustries.ResponseIndustriesGuideItem
import ru.practicum.android.diploma.data.dto.responses.vacancy.details.ResponseDetailsDto
import ru.practicum.android.diploma.data.dto.responses.vacancy.list.ResponseListDto
import ru.practicum.android.diploma.util.isConnected
import java.net.ConnectException

class RetrofitNetworkClient(private val hhService: HHApi, private val context: Context) : NetworkClient {
    override suspend fun getVacancies(request: SearchRequest): Result<ResponseListDto> {
        if (!isConnected(context)) {
            return Result.failure(ConnectException())
        }
        val response = withContext(Dispatchers.IO) {
            try {
                val result =
                    hhService.getVacancies(
                        searchOptionsStr = request.searchOptionsStr,
                        searchOptionsNum = request.searchOptionsNum,
                        isSalarySpecified = request.onlyWithSalary
                    )
                Result.success(result)
            } catch (e: HttpException) {
                Result.failure(e)
            }
        }
        return response
    }

    override suspend fun getCurrentVacancy(request: DetailsRequest): Result<ResponseDetailsDto> {
        if (!isConnected(context)) {
            return Result.failure(ConnectException())
        }
        val response = withContext(Dispatchers.IO) {
            try {
                val result = hhService.getVacancy(request.vacancyId)
                Result.success(result)
            } catch (e: HttpException) {
                Result.failure(e)
            }
        }
        return response
    }

    override suspend fun getIndustries(): Result<List<ResponseIndustriesGuideItem>> {
        if (!isConnected(context)) {
            return Result.failure(ConnectException())
        }
        val response = withContext(Dispatchers.IO) {
            try {
                val resultList = hhService.getIndustries()
                Result.success(resultList)
            } catch (e: HttpException) {
                Result.failure(e)
            }
        }
        return response
    }

    override suspend fun getAreas(request: GuideRequest): Result<ResponseAreaGuideDto> {
        if (!isConnected(context)) {
            return Result.failure(ConnectException())
        }
        val response = withContext(Dispatchers.IO) {
            try {
                val result = hhService.getAreas(request.id)
                Result.success(result)
            } catch (e: HttpException) {
                Result.failure(e)
            }
        }
        return response
    }

    override suspend fun getAllAreas(): Result<List<ResponseAreaGuideDto>> {
        if (!isConnected(context)) {
            return Result.failure(ConnectException())
        }
        val response = withContext(Dispatchers.IO) {
            try {
                val resultList = hhService.getAllAreas()
                Result.success(resultList)
            } catch (e: HttpException) {
                Result.failure(e)
            }
        }
        return response
    }

    override suspend fun getCountries(): Result<List<ResponseCountriesGuideItem>> {
        if (!isConnected(context)) {
            return Result.failure(ConnectException())
        }
        val response = withContext(Dispatchers.IO) {
            try {
                val resultList = hhService.getCountries()
                Result.success(resultList)
            } catch (e: HttpException) {
                Result.failure(e)
            }
        }
        return response
    }
}

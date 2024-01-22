package ru.practicum.android.diploma.data.network

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import ru.practicum.android.diploma.data.NetworkClient
import ru.practicum.android.diploma.data.dto.GuideRequest
import ru.practicum.android.diploma.data.dto.SearchRequest
import ru.practicum.android.diploma.data.dto.responses.Response
import ru.practicum.android.diploma.data.dto.responses.ResponseGuide
import ru.practicum.android.diploma.data.dto.responses.guides.countries.ResponseCountriesGuideItem
import ru.practicum.android.diploma.data.dto.responses.guides.inustries.ResponseIndustriesGuideItem
import ru.practicum.android.diploma.util.GlobalConstant
import ru.practicum.android.diploma.util.isConnected
import java.net.HttpURLConnection

class RetrofitNetworkClient(private val hhService: HHApi, private val context: Context) : NetworkClient {
    override suspend fun getVacancies(request: SearchRequest): Response {
        if (!isConnected(context)) {
            return Response().apply { resultCode = GlobalConstant.NO_INTERNET }
        }
        val response = withContext(Dispatchers.IO) {
            try {
                val result = hhService.getVacancies(request.searchOptions)
                result.apply { resultCode = HttpURLConnection.HTTP_OK }
            } catch (e: HttpException) {
                Response().apply { resultCode = HttpURLConnection.HTTP_INTERNAL_ERROR; error = e }
            }
        }
        return response
    }

    override suspend fun getCurrentVacancy(request: SearchRequest): Response {
        if (!isConnected(context)) {
            return Response().apply { resultCode = GlobalConstant.NO_INTERNET }
        }
        val response = withContext(Dispatchers.IO) {
            try {
                val result = hhService.getVacancy(request.vacancyId)
                result.apply { resultCode = HttpURLConnection.HTTP_OK }
            } catch (e: HttpException) {
                Response().apply { resultCode = HttpURLConnection.HTTP_INTERNAL_ERROR; error = e }
            }
        }
        return response
    }

    override suspend fun getSimilarVacancies(request: SearchRequest): Response {
        if (!isConnected(context)) {
            return Response().apply { resultCode = GlobalConstant.NO_INTERNET }
        }
        val response = withContext(Dispatchers.IO) {
            try {
                val result = hhService.getSimilarVacancies(request.vacancyId, request.searchOptions)
                result.apply { resultCode = HttpURLConnection.HTTP_OK }
            } catch (e: HttpException) {
                Response().apply { resultCode = HttpURLConnection.HTTP_INTERNAL_ERROR; error = e }
            }
        }
        return response
    }

    override suspend fun getIndustries(): ResponseGuide<ResponseIndustriesGuideItem> {
        if (!isConnected(context)) {
            return ResponseGuide<ResponseIndustriesGuideItem>().apply { resultCode = GlobalConstant.NO_INTERNET }
        }
        val response = withContext(Dispatchers.IO) {
            try {
                val resultList = hhService.getIndustries()
                val result = ResponseGuide<ResponseIndustriesGuideItem>().apply { listItem.addAll(resultList) }
                result.apply { resultCode = HttpURLConnection.HTTP_OK }
            } catch (e: HttpException) {
                ResponseGuide<ResponseIndustriesGuideItem>().apply {
                    resultCode = HttpURLConnection.HTTP_INTERNAL_ERROR; error = e
                }
            }
        }
        return response
    }

    override suspend fun getAreas(request: GuideRequest): Response {
        if (!isConnected(context)) {
            return Response().apply { resultCode = GlobalConstant.NO_INTERNET }
        }
        val response = withContext(Dispatchers.IO) {
            try {
                val result = hhService.getAreas(request.id)
                result.apply { resultCode = HttpURLConnection.HTTP_OK }
            } catch (e: HttpException) {
                Response().apply { resultCode = HttpURLConnection.HTTP_INTERNAL_ERROR; error = e }
            }
        }
        return response
    }

    override suspend fun getCountries(): ResponseGuide<ResponseCountriesGuideItem> {
        if (!isConnected(context)) {
            return ResponseGuide<ResponseCountriesGuideItem>().apply { resultCode = GlobalConstant.NO_INTERNET }
        }
        val response = withContext(Dispatchers.IO) {
            try {
                val resultList = hhService.getCountries()
                val result = ResponseGuide<ResponseCountriesGuideItem>().apply { listItem.addAll(resultList) }
                result.apply { resultCode = HttpURLConnection.HTTP_OK }
            } catch (e: HttpException) {
                ResponseGuide<ResponseCountriesGuideItem>().apply {
                    resultCode = HttpURLConnection.HTTP_INTERNAL_ERROR; error = e
                }
            }
        }
        return response
    }
}

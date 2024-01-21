package ru.practicum.android.diploma.data.network

import android.content.Context
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.practicum.android.diploma.data.NetworkClient
import ru.practicum.android.diploma.data.dto.GuideRequest
import ru.practicum.android.diploma.data.dto.SearchRequest
import ru.practicum.android.diploma.data.dto.responses.Response
import ru.practicum.android.diploma.data.dto.responses.ResponseGuide
import ru.practicum.android.diploma.data.dto.responses.guides.countries.ResponseCountriesGuideItem
import ru.practicum.android.diploma.data.dto.responses.guides.inustries.ResponseIndustriesGuideItem
import ru.practicum.android.diploma.util.isConnected

class RetrofitNetworkClient(private val hhService: HHApi, private val context: Context) : NetworkClient {
    override suspend fun getVacancies(request: SearchRequest): Response {
        if (isConnected(context) == false) {
            return Response().apply { resultCode = -1 }
        }
        return withContext(Dispatchers.IO) {
            try {
                val response = hhService.getVacancies(request.searchOptions)
                response.apply { resultCode = 200 }
            } catch (e: Throwable) {
                Response().apply { resultCode = 500 }
            }
        }
    }

    override suspend fun getCurrentVacancy(request: SearchRequest): Response {
        if (isConnected(context) == false) {
            return Response().apply { resultCode = -1 }
        }
        return withContext(Dispatchers.IO) {
            try {
                val response = hhService.getVacancy(request.vacancyId)
                response.apply { resultCode = 200 }
            } catch (e: Throwable) {
                Response().apply { resultCode = 500 }
            }
        }
    }

    override suspend fun getSimilarVacancies(request: SearchRequest): Response {
        if (isConnected(context) == false) {
            return Response().apply { resultCode = -1 }
        }
        return withContext(Dispatchers.IO) {
            try {
                val response = hhService.getSimilarVacancies(request.vacancyId, request.searchOptions)
                response.apply { resultCode = 200 }
            } catch (e: Throwable) {
                Response().apply { resultCode = 500 }
            }
        }
    }

    override suspend fun getIndustries(): ResponseGuide<ResponseIndustriesGuideItem> {
        if (isConnected(context) == false) {
            return ResponseGuide<ResponseIndustriesGuideItem>().apply { resultCode = 0 }
        }
        return withContext(Dispatchers.IO) {
            try {
                val result = hhService.getIndustries()
                val response = ResponseGuide<ResponseIndustriesGuideItem>().apply { listItem.addAll(result) }
                response.apply { resultCode = 200 }
            } catch (e: Throwable) {
                Log.d("MyLog", "${e.message}")
                ResponseGuide<ResponseIndustriesGuideItem>().apply { resultCode = 500 }
            }
        }
    }

    override suspend fun getAreas(request: GuideRequest): Response {
        if (isConnected(context) == false) {
            return Response().apply { resultCode = -1 }
        }
        return withContext(Dispatchers.IO) {
            try {
                val response = hhService.getAreas(request.id)
                response.apply { resultCode = 200 }
            } catch (e: Throwable) {
                Log.d("MyLog", "${e.message}")
                Response().apply { resultCode = 500 }
            }
        }
    }

    override suspend fun getCountries(): ResponseGuide<ResponseCountriesGuideItem> {
        if (isConnected(context) == false) {
            return ResponseGuide<ResponseCountriesGuideItem>().apply { resultCode = 0 }
        }
        return withContext(Dispatchers.IO) {
            try {
                val result = hhService.getCountries()
                val response = ResponseGuide<ResponseCountriesGuideItem>().apply { listItem.addAll(result) }
                response.apply { resultCode = 200 }
            } catch (e: Throwable) {
                Log.d("MyLog", "${e.message}")
                ResponseGuide<ResponseCountriesGuideItem>().apply { resultCode = 500 }
            }
        }
    }
}

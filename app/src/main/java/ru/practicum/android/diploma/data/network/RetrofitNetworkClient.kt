package ru.practicum.android.diploma.data.network

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.practicum.android.diploma.data.NetworkClient
import ru.practicum.android.diploma.data.dto.responses.Response
import ru.practicum.android.diploma.util.isConnected

class RetrofitNetworkClient(private val hhService: HHApi, private val context: Context) : NetworkClient {
    override suspend fun getVacancies(searchOptions: MutableMap<String, String>): Response {
        if (isConnected(context) == false) {
            return Response().apply { resultCode = -1 }
        }
        return withContext(Dispatchers.IO) {
            try {
                val response = hhService.getVacancies(searchOptions)
                response.apply { resultCode = 200 }
            } catch (e: Throwable) {
                Response().apply { resultCode = 500 }
            }
        }
    }

    override suspend fun getCurrentVacancy(vacancyId: String): Response {
        if (isConnected(context) == false) {
            return Response().apply { resultCode = -1 }
        }
        return withContext(Dispatchers.IO) {
            try {
                val response = hhService.getVacancy(vacancyId)
                response.apply { resultCode = 200 }
            } catch (e: Throwable) {
                Response().apply { resultCode = 500 }
            }
        }
    }

    override suspend fun getSimilarVacancies(vacancyId: String, searchOptions: MutableMap<String, String>): Response {
        if (isConnected(context) == false) {
            return Response().apply { resultCode = -1 }
        }
        return withContext(Dispatchers.IO) {
            try {
                val response = hhService.getSimilarVacancies(vacancyId,searchOptions)
                response.apply { resultCode = 200 }
            } catch (e: Throwable) {
                Response().apply { resultCode = 500 }
            }
        }
    }

    override suspend fun getIndustries(): Response {
        if (isConnected(context) == false) {
            return Response().apply { resultCode = -1 }
        }
        return withContext(Dispatchers.IO) {
            try {
                val response = hhService.getIndustries()
                response.apply { resultCode = 200 }
            } catch (e: Throwable) {
                Response().apply { resultCode = 500 }
            }
        }
    }

    override suspend fun getAreas(): Response {
        if (isConnected(context) == false) {
            return Response().apply { resultCode = -1 }
        }
        return withContext(Dispatchers.IO) {
            try {
                val response = hhService.getAreas()
                response.apply { resultCode = 200 }
            } catch (e: Throwable) {
                Response().apply { resultCode = 500 }
            }
        }
    }
}

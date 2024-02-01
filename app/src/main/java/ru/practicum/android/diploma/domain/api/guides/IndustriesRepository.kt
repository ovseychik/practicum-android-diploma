package ru.practicum.android.diploma.domain.api.guides

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.domain.models.SearchResultData
import ru.practicum.android.diploma.domain.models.guides.IndustryItem

interface IndustriesRepository {
    suspend fun getIndustries(): Flow<SearchResultData<List<IndustryItem>>>
}

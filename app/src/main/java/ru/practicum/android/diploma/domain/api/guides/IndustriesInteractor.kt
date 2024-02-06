package ru.practicum.android.diploma.domain.api.guides

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.domain.models.SearchResultData
import ru.practicum.android.diploma.domain.models.guides.IndustryItem

interface IndustriesInteractor {
    suspend fun getIndustries(): Flow<SearchResultData<List<IndustryItem>>>
    fun getIndustryFromSettings(): IndustryItem
    fun setIndustryInSettings(industry: IndustryItem)
}

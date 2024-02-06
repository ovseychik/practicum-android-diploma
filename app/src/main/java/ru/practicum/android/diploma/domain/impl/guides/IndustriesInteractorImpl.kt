package ru.practicum.android.diploma.domain.impl.guides

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.domain.api.guides.IndustriesInteractor
import ru.practicum.android.diploma.domain.api.guides.IndustriesRepository
import ru.practicum.android.diploma.domain.api.settings.SettingsRepository
import ru.practicum.android.diploma.domain.models.SearchResultData
import ru.practicum.android.diploma.domain.models.guides.IndustryItem

class IndustriesInteractorImpl(
    private val industriesRepository: IndustriesRepository,
    private val settingsRepository: SettingsRepository
) : IndustriesInteractor {
    override suspend fun getIndustries(): Flow<SearchResultData<List<IndustryItem>>> {
        return industriesRepository.getIndustries()
    }

    override fun getIndustryFromSettings(): IndustryItem {
        return settingsRepository.getIndustryFromSettings()
    }

    override fun setIndustryInSettings(industry: IndustryItem) {
        settingsRepository.setIndustryInSettings(industry)
    }
}

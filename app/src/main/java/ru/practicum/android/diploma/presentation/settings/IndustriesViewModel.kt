package ru.practicum.android.diploma.presentation.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.domain.api.guides.IndustriesInteractor
import ru.practicum.android.diploma.domain.models.SearchResultData
import ru.practicum.android.diploma.domain.models.guides.IndustryItem
import ru.practicum.android.diploma.presentation.settings.models.IndustriesScreenState

class IndustriesViewModel(private val industriesInteractor: IndustriesInteractor) : ViewModel() {

    private var _screenState: MutableLiveData<IndustriesScreenState> = MutableLiveData()
    val screenState: LiveData<IndustriesScreenState> = _screenState
    private val industriesList = mutableListOf<IndustryItem>()
    fun getIndustries() {
        viewModelScope.launch {
            industriesInteractor.getIndustries().collect {
                industriesList.clear()
                processingResult(it)
            }
        }
    }

    fun filteredIndustries(query: String) {
        val filteredList = industriesList
        filteredList.filter { it.industryName.substring(0, query.length) == query }
        if (filteredList.isEmpty()) {
            _screenState.postValue(IndustriesScreenState.Empty)
        } else {
            _screenState.postValue(IndustriesScreenState.Content(filteredList))
        }
    }

    fun saveSelectedIndustry(industry: IndustryItem) {
        industriesInteractor.setIndustryInSettings(industry)
    }

    private fun processingResult(result: SearchResultData<List<IndustryItem>>) {
        when (result) {
            is SearchResultData.Data -> {
                industriesList.addAll(result.value!!)
                industriesList.sortBy { it.industryName }
                _screenState.postValue(IndustriesScreenState.Content(industriesList))
            }

            is SearchResultData.ErrorServer -> {
                _screenState.postValue(IndustriesScreenState.Error(R.string.server_error))
            }

            is SearchResultData.NoInternet -> {
                _screenState.postValue(IndustriesScreenState.NoInternet(R.string.no_internet))
            }

            else -> {}
        }
    }
}

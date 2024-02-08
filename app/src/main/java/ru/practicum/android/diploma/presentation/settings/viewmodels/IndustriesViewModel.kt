package ru.practicum.android.diploma.presentation.settings.viewmodels

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
    private var selectedIndustry = industriesInteractor.getIndustryFromSettings()
    private val industriesList: MutableList<IndustryItem> = mutableListOf()
    private var filteredList = mutableListOf<IndustryItem>()
    fun getIndustries() {
        _screenState.postValue(IndustriesScreenState.Loading)
        viewModelScope.launch {
            industriesInteractor.getIndustries().collect {
                industriesList.clear()
                processingResult(it)
            }
        }
    }

    fun filteredIndustries(query: String) {
        val newFilteredList = industriesList
            .filter { query.length <= it.industryName.length && it.industryName.contains(query, true) }
        if (newFilteredList.isEmpty()) {
            _screenState.postValue(IndustriesScreenState.Empty)
        } else {
            filteredList.clear()
            filteredList.addAll(newFilteredList)
            _screenState.postValue(IndustriesScreenState.Content(newFilteredList, selectedIndustry.industryName))
        }
    }

    fun getSelectedIndustry(): IndustryItem {
        return industriesInteractor.getIndustryFromSettings()
    }

    fun saveSelectedIndustry() {
        if (selectedIndustry.industryName != "") {
            industriesInteractor.setIndustryInSettings(selectedIndustry)
        }

    }

    fun onIndustryItemClicked(industryItem: IndustryItem) {
        if (industryItem != selectedIndustry) {
            selectedIndustry = industryItem
            _screenState.postValue(IndustriesScreenState.Content(filteredList, industryItem.industryName))
        }
    }

    private fun processingResult(result: SearchResultData<List<IndustryItem>>) {
        when (result) {
            is SearchResultData.Data -> {
                industriesList.clear()
                industriesList.addAll(result.value!!)
                industriesList.sortBy { it.industryName }
                filteredList.clear()
                filteredList.addAll(industriesList)
                _screenState.postValue(IndustriesScreenState.Content(industriesList, selectedIndustry.industryName))
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

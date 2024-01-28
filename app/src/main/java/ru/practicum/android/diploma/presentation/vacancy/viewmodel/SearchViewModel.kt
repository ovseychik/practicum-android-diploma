package ru.practicum.android.diploma.presentation.vacancy.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.domain.api.VacanciesInteractor
import ru.practicum.android.diploma.domain.models.SearchResultData
import ru.practicum.android.diploma.domain.models.vacancy.Vacancies
import ru.practicum.android.diploma.presentation.vacancy.models.ScreenStateVacancies

class SearchViewModel(
    private val vacanciesInteractor: VacanciesInteractor
) : ViewModel() {

    private var _screenState: MutableLiveData<ScreenStateVacancies> = MutableLiveData()
    private var searchJob: Job? = null
    val screenState: LiveData<ScreenStateVacancies> = _screenState
    private var currentPage = 0
    private var isNextPageLoading = false
    private var currentQuery = ""
    private var foundItemsCount = 0

    fun getVacancies(query: String, pageNum: Int = 0) {
        if (query.isNotEmpty()) {
            if (pageNum != 0 && !isNextPageLoading) {
                isNextPageLoading = true
                _screenState.postValue(ScreenStateVacancies.NextPageIsLoading)
                viewModelScope.launch(Dispatchers.IO) {
                    vacanciesInteractor.getVacancies(query, pageNum).collect { result ->
                        processingResult(result)
                    }
                }
            }

            if (pageNum == 0) {
                _screenState.postValue(ScreenStateVacancies.IsLoading)
                viewModelScope.launch(Dispatchers.IO) {
                    vacanciesInteractor.getVacancies(query, pageNum).collect { result ->
                        processingResult(result)
                    }
                }
            }
        }
    }

    fun debounceSearch(query: String) {
        if (query != currentQuery) {
            searchJob?.cancel()
            searchJob = viewModelScope.launch {
                delay(SEARCH_DEBOUNCE_DELAY_MILLIS)
                getVacancies(query)
                currentPage = 0
                currentQuery = query
            }
        }
    }

    private fun processingResult(result: SearchResultData<Vacancies>) {
        when (result) {
            is SearchResultData.NoInternet -> {
                _screenState.postValue(ScreenStateVacancies.NoInternet(result.message))
            }

            is SearchResultData.ErrorServer -> {
                _screenState.postValue(ScreenStateVacancies.Error(result.message))
            }

            is SearchResultData.Empty -> {
                _screenState.postValue(ScreenStateVacancies.Empty(result.message))
            }

            is SearchResultData.Data -> {
                if (currentPage == 0) {
                    _screenState.postValue(
                        ScreenStateVacancies.Content(
                            result.value?.foundItems!!,
                            result.value.listVacancies
                        )
                    )
                    foundItemsCount = result.value.foundItems
                } else {
                    _screenState.postValue(
                        result.value?.let { ScreenStateVacancies.NextPageIsLoaded(it.listVacancies) }
                    )
                    isNextPageLoading = false
                }
            }
        }
    }

    fun onLastItemReached() {
        if (currentPage < foundItemsCount / ITEMS_PER_PAGE) {
            currentPage++
            getVacancies(currentQuery, currentPage)
        } else {
            // show toast
        }
    }

    companion object {
        const val SEARCH_DEBOUNCE_DELAY_MILLIS = 2000L
        const val ITEMS_PER_PAGE = 20
    }
}

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
import ru.practicum.android.diploma.presentation.vacancy.models.PageLoadingState
import ru.practicum.android.diploma.presentation.vacancy.models.ScreenStateVacancies
import ru.practicum.android.diploma.presentation.vacancy.models.SingleLiveEvent

class SearchViewModel(
    private val vacanciesInteractor: VacanciesInteractor
) : ViewModel() {

    private val _screenState: MutableLiveData<ScreenStateVacancies> = MutableLiveData()
    private val showToast = SingleLiveEvent<PageLoadingState>()
    private var searchJob: Job? = null
    val screenState: LiveData<ScreenStateVacancies> = _screenState
    val toastState: LiveData<PageLoadingState> = showToast
    private var currentPage = FIRST_PAGE
    private var isNextPageLoading = false
    private var currentQuery = EMPTY_QUERY
    private var foundItemsCount = ZERO_COUNT

    fun getVacancies(query: String, pageNum: Int = FIRST_PAGE) {
        if (pageNum != FIRST_PAGE && !isNextPageLoading) {
            isNextPageLoading = true
            _screenState.postValue(ScreenStateVacancies.NextPageIsLoading)
        }
        if (pageNum == FIRST_PAGE) {
            _screenState.postValue(ScreenStateVacancies.IsLoading)
        }
        viewModelScope.launch(Dispatchers.IO) {
            vacanciesInteractor.getVacancies(query, pageNum).collect { result ->
                processingResult(result)
            }
        }
    }

    fun debounceSearch(query: String) {
        if (query != currentQuery) {
            currentQuery = query
            searchJob?.cancel()
            if (currentQuery.length > ONE_LETTER) {
                searchJob = viewModelScope.launch {
                    delay(SEARCH_DEBOUNCE_DELAY_MILLIS)
                    currentPage = FIRST_PAGE
                    getVacancies(currentQuery)
                }
            }
        }
    }

    private fun processingResult(result: SearchResultData<Vacancies>) {
        when (result) {
            is SearchResultData.NoInternet -> {
                if (currentPage == FIRST_PAGE) {
                    _screenState.postValue(ScreenStateVacancies.NoInternet(result.message))
                } else {
                    isNextPageLoading = false
                    showToast.postValue(PageLoadingState.InternetError)
                }
            }

            is SearchResultData.ErrorServer -> {
                if (currentPage == FIRST_PAGE) {
                    _screenState.postValue(ScreenStateVacancies.Error(result.message))
                } else {
                    isNextPageLoading = false
                    showToast.postValue(PageLoadingState.ServerError)
                }
            }

            is SearchResultData.Empty -> {
                _screenState.postValue(ScreenStateVacancies.Empty(result.message))
            }

            is SearchResultData.Data -> {
                if (currentPage == FIRST_PAGE) {
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
        }
    }

    companion object {
        const val SEARCH_DEBOUNCE_DELAY_MILLIS = 2000L
        const val ITEMS_PER_PAGE = 20
        const val FIRST_PAGE = 0
        const val EMPTY_QUERY = ""
        const val ZERO_COUNT = 0
        const val ONE_LETTER = 1
    }
}

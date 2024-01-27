package ru.practicum.android.diploma.presentation.view_model

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
import ru.practicum.android.diploma.presentation.models.ScreenStateVacancies

class SearhViewModel(
    private val vacanciesInteractor: VacanciesInteractor
) : ViewModel() {

    private var _screenState: MutableLiveData<ScreenStateVacancies> = MutableLiveData()
    private var searchJob: Job? = null
    val screenState: LiveData<ScreenStateVacancies> = _screenState

    fun getVacancies(query: String, pageNum: Int = 0) {
        if (query.isNotEmpty()) {
            _screenState.postValue(ScreenStateVacancies.IsLoading)
            viewModelScope.launch(Dispatchers.IO) {
                vacanciesInteractor.getVacancies(query, pageNum).collect { result ->
                    processingResult(result)
                }
            }
        }
    }

    fun debounceSearch(query: String) {
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(SEARCH_DEBOUNCE_DELAY_MILLIS)
            getVacancies(query)
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
                _screenState.postValue(
                    ScreenStateVacancies.Content(
                        result.value?.foundItems!!,
                        result.value.listVacancies
                    )
                )
            }
        }
    }

    companion object {
        const val SEARCH_DEBOUNCE_DELAY_MILLIS = 2000L
    }
}

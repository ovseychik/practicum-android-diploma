package ru.practicum.android.diploma.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.domain.api.DetailsInteractor
import ru.practicum.android.diploma.domain.models.SearchResultData
import ru.practicum.android.diploma.domain.models.vacancy.VacancyDetails
import ru.practicum.android.diploma.presentation.models.ScreenStateDetails

class ViewModelDetails(private val detailsInteractor: DetailsInteractor) : ViewModel() {
    private var _screenState: MutableLiveData<ScreenStateDetails> = MutableLiveData()
    val screenState: LiveData<ScreenStateDetails> = _screenState

    fun getVacancyDetails(vacancyId: String) {
        _screenState.postValue(ScreenStateDetails.IsLoading)
        viewModelScope.launch {
            detailsInteractor.getVacancyDetails(vacancyId).collect { result ->
                processingResult(result)
            }
        }
    }

    private fun processingResult(result: SearchResultData<VacancyDetails>) {
        when (result) {
            is SearchResultData.NoInternet -> {
                _screenState.postValue(ScreenStateDetails.NoInternet(result.message))
            }

            is SearchResultData.ErrorServer -> {
                _screenState.postValue(ScreenStateDetails.Error(result.message))
            }

            is SearchResultData.Data -> {
                _screenState.postValue(ScreenStateDetails.Content(result.value!!))
            }

            else -> {}
        }
    }
}

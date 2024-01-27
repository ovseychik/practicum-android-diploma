package ru.practicum.android.diploma.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.practicum.android.diploma.domain.api.DetailsInteractor
import ru.practicum.android.diploma.domain.models.SearchResultData
import ru.practicum.android.diploma.domain.models.vacancy.VacancyDetails
import ru.practicum.android.diploma.presentation.models.ScreenStateDetails

class ViewModelDetails(private val detailsInteractor: DetailsInteractor) {
    private var _screenState: MutableLiveData<ScreenStateDetails> = MutableLiveData()
    val screenState: LiveData<ScreenStateDetails> = _screenState

    suspend fun getVacancies(query: String) {
        _screenState.postValue(ScreenStateDetails.IsLoading)
        detailsInteractor.getVacancyDetails(query).collect { result ->
            processingResult(result)
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

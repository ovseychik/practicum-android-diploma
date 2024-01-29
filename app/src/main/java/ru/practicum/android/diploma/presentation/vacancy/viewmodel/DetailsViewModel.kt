package ru.practicum.android.diploma.presentation.vacancy.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.domain.api.DetailsInteractor
import ru.practicum.android.diploma.domain.api.ExternalNavigator
import ru.practicum.android.diploma.domain.models.SearchResultData
import ru.practicum.android.diploma.domain.models.vacancy.VacancyDetails
import ru.practicum.android.diploma.presentation.vacancy.models.ScreenStateDetails

class DetailsViewModel(
    private val detailsInteractor: DetailsInteractor,
    private val externalNavigator: ExternalNavigator,
) : ViewModel() {
    private var _screenState: MutableLiveData<ScreenStateDetails> = MutableLiveData()
    val screenState: LiveData<ScreenStateDetails> = _screenState
    private var _currentVacancyInFavorite: MutableLiveData<Boolean> =
        MutableLiveData(false)
    val currentVacancyInFavorite: LiveData<Boolean> = _currentVacancyInFavorite
    private var _isToastShowing: MutableLiveData<Boolean> = MutableLiveData(false)
    val isToastShowing: LiveData<Boolean> = _isToastShowing
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

    fun sharingLink(vacancyUrl: String) {
        externalNavigator.shareLink(vacancyUrl)
    }

    fun sendEmail(email: String) {
        externalNavigator.openEmail(email)
        if (externalNavigator.getExceptionsList() != ZERO) {
            _isToastShowing.postValue(true)
        }
    }

    fun openDial(number: String) {
        externalNavigator.openDial(number)
    }

    fun changedInFavorite() {
        _currentVacancyInFavorite.postValue(false)
//        ("изменение избранности")
    }
}

private const val ZERO = 0

package ru.practicum.android.diploma.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.practicum.android.diploma.domain.api.VacanciesInteractor
import ru.practicum.android.diploma.domain.models.SearchResultData
import ru.practicum.android.diploma.domain.models.vacancy.Vacancies
import ru.practicum.android.diploma.presentation.models.ScreenStateVacancies

class ViewModelVacancies(
    private val vacanciesInteractor: VacanciesInteractor
) : ViewModel() {

    private var _screenState: MutableLiveData<ScreenStateVacancies> = MutableLiveData()

    val screenState: LiveData<ScreenStateVacancies> = _screenState
    suspend fun getVacancies(query: String, pageNum: Int = 0) {
        if (query.isNotEmpty()) {
            _screenState.postValue(ScreenStateVacancies.IsLoading)
            vacanciesInteractor.getVacancies(query, pageNum).collect { result ->
                processingResult(result)
            }
        }
    }

    private fun processingResult(result: SearchResultData<Vacancies>) {
        when (result) {
            is SearchResultData.NoInternet -> {
                _screenState.postValue(ScreenStateVacancies.NoInternet(result.message))
            }

            is SearchResultData.ErrorServer -> {
                _screenState.postValue(ScreenStateVacancies.NoInternet(result.message))
            }

            is SearchResultData.Empty -> {
                _screenState.postValue(ScreenStateVacancies.NoInternet(result.message))
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
}

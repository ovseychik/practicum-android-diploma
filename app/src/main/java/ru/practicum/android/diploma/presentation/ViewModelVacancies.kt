package ru.practicum.android.diploma.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.practicum.android.diploma.domain.api.VacanciesInteractor
import ru.practicum.android.diploma.domain.models.SearchResultData
import ru.practicum.android.diploma.domain.models.vacancy.Vacancies
import ru.practicum.android.diploma.presentation.models.VacanciesScreenState

class ViewModelVacancies(
    private val vacanciesInteractor: VacanciesInteractor
) : ViewModel() {

    private var _screenState: MutableLiveData<VacanciesScreenState> = MutableLiveData()

    val screenState: LiveData<VacanciesScreenState> = _screenState
    suspend fun getVacancies(query: String, pageNum: Int = 0) {
        if (query.isNotEmpty()) {
            _screenState.postValue(VacanciesScreenState.IsLoading)
            vacanciesInteractor.getVacancies(query, pageNum).collect { result ->
                processingResult(result)
            }
        }
    }

    private fun processingResult(result: SearchResultData<Vacancies>) {
        when (result) {
            is SearchResultData.NoInternet -> {
                _screenState.postValue(VacanciesScreenState.NoInternet(result.message))
            }

            is SearchResultData.ErrorServer -> {
                _screenState.postValue(VacanciesScreenState.NoInternet(result.message))
            }

            is SearchResultData.Empty -> {
                _screenState.postValue(VacanciesScreenState.NoInternet(result.message))
            }

            is SearchResultData.Data -> {
                _screenState.postValue(
                    VacanciesScreenState.Content(
                        result.value?.foundItems!!,
                        result.value.listVacancies
                    )
                )
            }
        }
    }
}

package ru.practicum.android.diploma.presentation.settings.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ru.practicum.android.diploma.data.models.EMPTY_PARAM_NUM
import ru.practicum.android.diploma.data.models.EMPTY_PARAM_SRT
import ru.practicum.android.diploma.data.models.ValuesSearchId
import ru.practicum.android.diploma.domain.api.settings.SettingsInteractor
import ru.practicum.android.diploma.domain.models.settings.SearchSettings
import ru.practicum.android.diploma.domain.models.settings.setDefault
import ru.practicum.android.diploma.util.debounce

class SettingsViewModel(private val settingsInteractor: SettingsInteractor) : ViewModel() {

    private val updateSalaryDebounce =
        debounce<String>(UPDATE_DEBOUNCE_DELAY, viewModelScope, true) {
            saveSalary(it)
        }
    private var _screenState: MutableLiveData<SearchSettings> = MutableLiveData()
    val screenState: LiveData<SearchSettings> = _screenState
    private var _isSettingsModifed: MutableLiveData<Boolean> = MutableLiveData(false)
    val isSettingsModified: LiveData<Boolean> = _isSettingsModifed
    private var currentSettings = setDefault()
    fun getSettings() {
        currentSettings = settingsInteractor.getSettings()
        compareSettings()
        _screenState.postValue(currentSettings)
    }

    private fun compareSettings() {
        currentSettings = settingsInteractor.getSettings()
        if (currentSettings.settingsId == ValuesSearchId.MODIFIED) {
            _isSettingsModifed.postValue(true)
        } else {
            _isSettingsModifed.postValue(false)
        }
    }

    fun savedIsSalarySpecified(newValue: Boolean) {
        if (currentSettings.isSalarySpecified != newValue) {
            settingsInteractor.saveSettings(
                currentSettings.copy(
                    isSalarySpecified = newValue,
                    settingsId = ValuesSearchId.MODIFIED
                )
            )
            getSettings()
        }
    }

    private fun saveSalary(newSalary: String) {
        if (currentSettings.salary.toString() != newSalary) {
            if (newSalary == EMPTY_PARAM_SRT) {
                settingsInteractor.saveSettings(
                    currentSettings.copy(
                        salary = EMPTY_PARAM_NUM,
                        settingsId = ValuesSearchId.MODIFIED
                    )
                )
            } else {
                settingsInteractor.saveSettings(
                    currentSettings.copy(
                        salary = newSalary.toInt(),
                        settingsId = ValuesSearchId.MODIFIED
                    )
                )
            }
            getSettings()
        }
    }

    fun updateSalary(newSalary: String) {
        updateSalaryDebounce(newSalary)
    }

    fun saveSettingsByClickConfirm() {
        settingsInteractor.saveSettings(currentSettings.copy(settingsId = ValuesSearchId.BASE))
    }

    fun deletedSettings() {
        val newSettings = setDefault()
        settingsInteractor.saveSettings(newSettings.copy(ValuesSearchId.MODIFIED))
        getSettings()
    }

    companion object {
        private const val UPDATE_DEBOUNCE_DELAY = 500L
    }
}

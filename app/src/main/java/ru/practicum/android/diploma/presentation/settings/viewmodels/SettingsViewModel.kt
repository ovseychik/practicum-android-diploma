package ru.practicum.android.diploma.presentation.settings.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.practicum.android.diploma.data.models.EMPTY_PARAM_NUM
import ru.practicum.android.diploma.data.models.EMPTY_PARAM_SRT
import ru.practicum.android.diploma.data.models.ValuesSearchId
import ru.practicum.android.diploma.domain.api.settings.SettingsInteractor
import ru.practicum.android.diploma.domain.models.settings.SearchSettings
import ru.practicum.android.diploma.domain.models.settings.setDefault

class SettingsViewModel(private val settingsInteractor: SettingsInteractor) : ViewModel() {

    private var _screenState: MutableLiveData<SearchSettings> = MutableLiveData()
    val screenState: LiveData<SearchSettings> = _screenState
    private var _isSettingsModifed: MutableLiveData<Boolean> = MutableLiveData(false)
    val isSettingsModified: LiveData<Boolean> = _isSettingsModifed
    private var currentSettings = setDefault()
    private var currentSalary = ""
    fun getSettings() {
        currentSettings = settingsInteractor.getSettings()
        if (currentSettings.salary == EMPTY_PARAM_NUM) {
            currentSalary = EMPTY_PARAM_SRT
        } else {
            currentSalary = currentSettings.salary.toString()
        }
        compareSettings()
        _screenState.postValue(currentSettings)
    }

    private fun compareSettings() {
        if (currentSettings.settingsId == ValuesSearchId.MODIFIED) {
            _isSettingsModifed.postValue(true)
        } else {
            _isSettingsModifed.postValue(false)
        }
    }

    fun savedIsSalarySpecified(newValue: Boolean) {
        if (currentSettings.isSalarySpecified != newValue) {
            currentSettings = currentSettings.copy(
                isSalarySpecified = newValue,
                settingsId = ValuesSearchId.MODIFIED
            )
            settingsInteractor.saveSettings(currentSettings)
            compareSettings()
        }
    }

    fun saveSalary(newSalary: String) {
        if (currentSalary != newSalary) {
            if (newSalary == EMPTY_PARAM_SRT) {
                currentSettings = currentSettings.copy(
                    salary = EMPTY_PARAM_NUM,
                    settingsId = ValuesSearchId.MODIFIED
                )
                settingsInteractor.saveSettings(currentSettings)
            } else {
                currentSettings = currentSettings.copy(
                    salary = newSalary.toInt(),
                    settingsId = ValuesSearchId.MODIFIED
                )
                settingsInteractor.saveSettings(currentSettings)
            }
            compareSettings()
        }
    }

    fun saveSettingsByClickConfirm() {
        settingsInteractor.saveSettings(currentSettings.copy(settingsId = ValuesSearchId.BASE))
    }

    fun deletedSettings() {
        val newSettings = setDefault()
        settingsInteractor.saveSettings(newSettings.copy(ValuesSearchId.MODIFIED))
        getSettings()
    }

}

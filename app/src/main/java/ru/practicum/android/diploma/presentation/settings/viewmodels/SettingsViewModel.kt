package ru.practicum.android.diploma.presentation.settings.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.practicum.android.diploma.data.models.ValuesSearchId
import ru.practicum.android.diploma.domain.api.settings.SettingsInteractor
import ru.practicum.android.diploma.domain.models.settings.SearchSettings
import ru.practicum.android.diploma.domain.models.settings.setDefault

class SettingsViewModel(private val settingsInteractor: SettingsInteractor) : ViewModel() {

    init {
        getSettings()
        compareSettings()
    }

    private var _screenState: MutableLiveData<SearchSettings> = MutableLiveData()
    val screenState: LiveData<SearchSettings> = _screenState
    private var _isSettingsModifed: MutableLiveData<Boolean> = MutableLiveData(false)
    val isSettingsModifed: LiveData<Boolean> = _isSettingsModifed
    private var currentSettings = setDefault()
    fun getSettings() {
        currentSettings = settingsInteractor.getSettings()
        _screenState.postValue(currentSettings)
    }

    fun compareSettings() {
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
            compareSettings()
        }
    }

    fun saveSalary(newSalary: String) {
        if (currentSettings.salary != newSalary.toInt()) {
            settingsInteractor.saveSettings(
                currentSettings.copy(
                    salary = newSalary.toInt(),
                    settingsId = ValuesSearchId.MODIFIED
                )
            )
            compareSettings()
        }
    }

    fun saveSettingsByClickConfirm() {
        settingsInteractor.saveSettings(currentSettings.copy(settingsId = ValuesSearchId.BASE))
    }
}

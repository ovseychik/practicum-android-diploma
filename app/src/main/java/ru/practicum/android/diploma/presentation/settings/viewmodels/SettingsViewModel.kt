package ru.practicum.android.diploma.presentation.settings.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.practicum.android.diploma.data.models.EMPTY_PARAM_NUM
import ru.practicum.android.diploma.data.models.EMPTY_PARAM_SRT
import ru.practicum.android.diploma.data.models.ValuesSearchId
import ru.practicum.android.diploma.domain.api.settings.SettingsInteractor
import ru.practicum.android.diploma.domain.models.guides.Country
import ru.practicum.android.diploma.domain.models.guides.IndustryItem
import ru.practicum.android.diploma.domain.models.guides.PlaceItem
import ru.practicum.android.diploma.domain.models.settings.SearchSettings
import ru.practicum.android.diploma.domain.models.settings.setDefault

class SettingsViewModel(private val settingsInteractor: SettingsInteractor) : ViewModel() {

    private var _screenState: MutableLiveData<SearchSettings> = MutableLiveData()
    val screenState: LiveData<SearchSettings> = _screenState
    private var _isSettingsModifed: MutableLiveData<Boolean> = MutableLiveData(false)
    val isSettingsModified: LiveData<Boolean> = _isSettingsModifed
    private var currentSettings = setDefault()
    private var baseSettings = settingsInteractor.getSettings()
    private var currentSalary = if (baseSettings.salary == EMPTY_PARAM_NUM) {
        EMPTY_PARAM_SRT
    } else {
        baseSettings.salary.toString()
    }

    fun getSettings() {
        currentSettings = settingsInteractor.getSettings()
        compareSettings()
        _screenState.postValue(currentSettings)
    }

    private fun compareSettings() {
        if (baseSettings.country.countryId != currentSettings.country.countryId
            || baseSettings.place.areaId != currentSettings.place.areaId
            || baseSettings.industry.industryName != currentSettings.industry.industryName
        ) {
            currentSettings = currentSettings.copy(settingsId = ValuesSearchId.MODIFIED)
            settingsInteractor.saveSettings(currentSettings)
        }
        if (currentSettings.settingsId == ValuesSearchId.MODIFIED) {
            _isSettingsModifed.postValue(true)
        } else {
            _isSettingsModifed.postValue(false)
        }
    }

    fun savedIsSalarySpecified(newValue: Boolean) {
        if (baseSettings.isSalarySpecified != newValue) {
            val modified = ValuesSearchId.MODIFIED
            currentSettings = currentSettings.copy(
                isSalarySpecified = newValue,
                settingsId = modified
            )
            settingsInteractor.saveSettings(currentSettings)
        } else {
            val modified = ValuesSearchId.BASE
            currentSettings = currentSettings.copy(settingsId = modified)
        }
        compareSettings()
    }

    fun saveSalary(newSalary: String) {
        if (currentSalary != newSalary) {
            val modified = ValuesSearchId.MODIFIED
            if (newSalary == EMPTY_PARAM_SRT) {
                currentSettings = currentSettings.copy(
                    salary = EMPTY_PARAM_NUM,
                    settingsId = modified
                )
                settingsInteractor.saveSettings(currentSettings)
            } else {
                currentSettings = currentSettings.copy(
                    salary = newSalary.toInt(),
                    settingsId = modified
                )
                settingsInteractor.saveSettings(currentSettings)
            }
        } else {
            val modified = ValuesSearchId.BASE
            currentSettings = currentSettings.copy(settingsId = modified)
        }
        compareSettings()
    }

    fun deletePlace() {
        currentSettings = currentSettings.copy(country = Country("", ""), place = PlaceItem("", ""))
        settingsInteractor.saveSettings(currentSettings)
        getSettings()
    }

    fun deleteIndustry() {
        currentSettings = currentSettings.copy(industry = IndustryItem(""))
        settingsInteractor.saveSettings(currentSettings)
        getSettings()
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

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
    private var _isSettingIsNotEmpty: MutableLiveData<Boolean> = MutableLiveData(false)
    val isSettingIsNotEmpty: LiveData<Boolean> = _isSettingIsNotEmpty
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
            || baseSettings.industry.industryId != currentSettings.industry.industryId
            || baseSettings.salary != currentSettings.salary
            || baseSettings.isSalarySpecified != currentSettings.isSalarySpecified
        ) {
            currentSettings = currentSettings.copy(settingsId = ValuesSearchId.MODIFIED)
            settingsInteractor.saveSettings(currentSettings)
        } else {
            currentSettings = currentSettings.copy(settingsId = ValuesSearchId.BASE)
            settingsInteractor.saveSettings(currentSettings)
        }
        if (currentSettings.settingsId == ValuesSearchId.MODIFIED) {
            _isSettingsModifed.postValue(true)
        } else {
            _isSettingsModifed.postValue(false)
        }
        if (isSettingsNotEmpty(currentSettings)) {
            _isSettingIsNotEmpty.postValue(true)
        } else {
            _isSettingIsNotEmpty.postValue(false)
        }
    }

    private fun isSettingsNotEmpty(settings: SearchSettings): Boolean {
        return !(
            !settings.isSalarySpecified
                && settings.salary == EMPTY_PARAM_NUM
                && settings.country.countryId == EMPTY_PARAM_SRT
                && settings.place.areaId == EMPTY_PARAM_SRT
                && settings.industry.industryId == EMPTY_PARAM_SRT
            )
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
            currentSettings = currentSettings.copy(isSalarySpecified = newValue, settingsId = modified)
            settingsInteractor.saveSettings(currentSettings)
        }
        compareSettings()
    }

    fun saveSalary(newSalary: String) {
        var savedSalary = EMPTY_PARAM_NUM
        savedSalary = if (newSalary == EMPTY_PARAM_SRT) {
            EMPTY_PARAM_NUM
        } else {
            newSalary.toInt()
        }
        if (currentSalary != newSalary) {
            val modified = ValuesSearchId.MODIFIED
            currentSettings = currentSettings.copy(
                salary = savedSalary,
                settingsId = modified
            )
            settingsInteractor.saveSettings(currentSettings)
        } else {
            val modified = ValuesSearchId.BASE
            currentSettings = currentSettings.copy(
                salary = savedSalary,
                settingsId = modified
            )
            settingsInteractor.saveSettings(currentSettings)
        }
        compareSettings()
    }

    fun deletePlace() {
        currentSettings = currentSettings.copy(
            country = Country(EMPTY_PARAM_SRT, EMPTY_PARAM_SRT),
            place = PlaceItem(EMPTY_PARAM_SRT, EMPTY_PARAM_SRT)
        )
        settingsInteractor.saveSettings(currentSettings)
        getSettings()
    }

    fun deleteIndustry() {
        currentSettings = currentSettings.copy(industry = IndustryItem(EMPTY_PARAM_SRT, EMPTY_PARAM_SRT))
        settingsInteractor.saveSettings(currentSettings)
        getSettings()
    }

    fun saveSettingsByClickConfirm() {
        settingsInteractor.saveSettings(currentSettings.copy(settingsId = ValuesSearchId.BASE))
    }

    fun deletedSettings() {
        val newSettings = setDefault()
        settingsInteractor.saveSettings(newSettings.copy(settingsId = ValuesSearchId.MODIFIED))
        getSettings()
    }
}

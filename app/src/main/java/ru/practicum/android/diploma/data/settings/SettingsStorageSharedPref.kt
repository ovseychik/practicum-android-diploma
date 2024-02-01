package ru.practicum.android.diploma.data.settings

import android.content.SharedPreferences
import com.google.gson.Gson
import ru.practicum.android.diploma.data.SettingsStorage
import ru.practicum.android.diploma.data.models.SearchSettingsData

const val SEARCHING_OPTIONS = "searching_options"

class SettingsStorageSharedPref(private val settingssharedPref: SharedPreferences, private val json: Gson) :
    SettingsStorage {
    override fun getSettings(): SearchSettingsData {
        val settings = settingssharedPref.getString(SEARCHING_OPTIONS, "")
        return if (settings != "") json.fromJson(settings, SearchSettingsData::class.java) else SearchSettingsData()
    }

    override fun saveSettings(settings: SearchSettingsData) {
        settingssharedPref.edit().putString(SEARCHING_OPTIONS, json.toJson(settings)).apply()
    }

}

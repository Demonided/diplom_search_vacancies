package ru.practicum.android.diploma.data.filter.storage.impl

import android.content.SharedPreferences
import com.google.gson.Gson
import ru.practicum.android.diploma.domain.filter.datashared.CountryShared
import ru.practicum.android.diploma.domain.filter.storage.CountryFilterStorage

class CountryFilterStorageImpl(
    private val sharedPreferences: SharedPreferences,
    private val gson: Gson
) :
    CountryFilterStorage {

    override fun saveCountryState(country: CountryShared?) {
        val json = gson.toJson(country)
        sharedPreferences.edit().putString(FiltersLocalStorage.KEY_COUNTRY, json).apply()
    }

    override fun loadCountryState(): CountryShared? {
        val json = sharedPreferences.getString(FiltersLocalStorage.KEY_COUNTRY, null)
        return gson.fromJson(json, CountryShared::class.java)
    }
}

package ru.practicum.android.diploma.data.filter.storage.impl

import android.content.SharedPreferences
import com.google.gson.Gson
import ru.practicum.android.diploma.domain.filter.datashared.IndustriesShared
import ru.practicum.android.diploma.domain.filter.storage.IndustriesFilterStorage

class IndustriesFilterStorageImpl(
    private val sharedPreferences: SharedPreferences,
    private val gson: Gson
) : IndustriesFilterStorage {
    override fun saveIndustriesState(industries: IndustriesShared?) {
        val json = gson.toJson(industries)
        sharedPreferences.edit().putString(FiltersLocalStorage.KEY_INDUSTRIES, json).apply()
    }

    override fun loadIndustriesState(): IndustriesShared? {
        val json = sharedPreferences.getString(FiltersLocalStorage.KEY_INDUSTRIES, null)
        return gson.fromJson(json, IndustriesShared::class.java)
    }
}

package ru.practicum.android.diploma.data.filter.storage.impl

import android.content.SharedPreferences
import com.google.gson.Gson
import ru.practicum.android.diploma.domain.filter.datashared.RegionShared
import ru.practicum.android.diploma.domain.filter.storage.RegionFilterStorage

class RegionFilterStorageImpl(
    private val sharedPreferences: SharedPreferences,
    private val gson: Gson
) : RegionFilterStorage {
    override fun saveRegionState(region: RegionShared?) {
        val json = gson.toJson(region)
        sharedPreferences.edit().putString(FiltersLocalStorage.KEY_REGION, json).apply()
    }

    override fun loadRegionState(): RegionShared? {
        val json = sharedPreferences.getString(FiltersLocalStorage.KEY_REGION, null)
        return gson.fromJson(json, RegionShared::class.java)
    }
}

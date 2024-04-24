package ru.practicum.android.diploma.data.filter.storage.impl

import android.content.SharedPreferences
import com.google.gson.Gson
import ru.practicum.android.diploma.domain.filter.datashared.SalaryBooleanShared
import ru.practicum.android.diploma.domain.filter.storage.SalaryBooleanFilterStorage

class SalaryBooleanFilterStorageImpl(
    private val sharedPreferences: SharedPreferences,
    private val gson: Gson
) :
    SalaryBooleanFilterStorage {
    override fun saveSalaryBooleanState(salary: SalaryBooleanShared?) {
        val json = gson.toJson(salary)
        sharedPreferences.edit().putString(FiltersLocalStorage.KEY_SALARY_BOOLEAN, json).apply()
    }

    override fun loadSalaryBooleanState(): SalaryBooleanShared? {
        val json = sharedPreferences.getString(FiltersLocalStorage.KEY_SALARY_BOOLEAN, null)
        return gson.fromJson(json, SalaryBooleanShared::class.java)
    }
}

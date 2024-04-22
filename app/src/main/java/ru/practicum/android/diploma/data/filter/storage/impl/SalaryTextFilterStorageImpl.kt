package ru.practicum.android.diploma.data.filter.storage.impl

import android.content.SharedPreferences
import com.google.gson.Gson
import ru.practicum.android.diploma.domain.filter.datashared.SalaryTextShared
import ru.practicum.android.diploma.domain.filter.storage.SalaryTextFilterStorage

class SalaryTextFilterStorageImpl(
    private val sharedPreferences: SharedPreferences,
    private val gson: Gson
) : SalaryTextFilterStorage {
    override fun saveSalaryTextState(salary: SalaryTextShared?) {
        val json = gson.toJson(salary)
        sharedPreferences.edit().putString(FiltersLocalStorage.KEY_SALARY_TEXT, json).apply()
    }

    override fun loadSalaryTextState(): SalaryTextShared? {
        val json = sharedPreferences.getString(FiltersLocalStorage.KEY_SALARY_TEXT, null)
        return gson.fromJson(json, SalaryTextShared::class.java)
    }
}

package ru.practicum.android.diploma.domain.filter.storage

import ru.practicum.android.diploma.domain.filter.datashared.CountryShared
import ru.practicum.android.diploma.domain.filter.datashared.IndustriesShared
import ru.practicum.android.diploma.domain.filter.datashared.RegionShared
import ru.practicum.android.diploma.domain.filter.datashared.SalaryBooleanShared
import ru.practicum.android.diploma.domain.filter.datashared.SalaryTextShared
import ru.practicum.android.diploma.domain.models.FiltersSettings

interface FiltersStorage {

    fun getPrefs(): FiltersSettings
    fun savePrefs(settings: FiltersSettings)
    fun clearPrefs()
    fun saveCountryState(country: CountryShared?)
    fun loadCountryState(): CountryShared?
    fun saveRegionState(region: RegionShared?)
    fun loadRegionState(): RegionShared?
    fun saveIndustriesState(industries: IndustriesShared?)
    fun loadIndustriesState(): IndustriesShared?
    fun saveSalaryTextState(salary: SalaryTextShared?)
    fun loadSalaryTextState(): SalaryTextShared?
    fun saveSalaryBooleanState(salary: SalaryBooleanShared?)
    fun loadSalaryBooleanState(): SalaryBooleanShared?
}

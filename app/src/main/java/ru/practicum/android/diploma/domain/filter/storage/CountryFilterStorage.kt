package ru.practicum.android.diploma.domain.filter.storage

import ru.practicum.android.diploma.domain.filter.datashared.CountryShared

interface CountryFilterStorage {
    fun saveCountryState(country: CountryShared?)
    fun loadCountryState(): CountryShared?
}

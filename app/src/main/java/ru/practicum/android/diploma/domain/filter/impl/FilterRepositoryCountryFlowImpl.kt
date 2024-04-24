package ru.practicum.android.diploma.domain.filter.impl

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import ru.practicum.android.diploma.domain.filter.FilterRepositoryCountryFlow
import ru.practicum.android.diploma.domain.filter.datashared.CountryShared
import ru.practicum.android.diploma.domain.filter.storage.CountryFilterStorage

class FilterRepositoryCountryFlowImpl(
    private val sharedPreferences: CountryFilterStorage
) : FilterRepositoryCountryFlow {

    private val countryFlow: MutableStateFlow<CountryShared?> = MutableStateFlow(sharedPreferences.loadCountryState())

    override fun setCountryFlow(country: CountryShared?) {
        sharedPreferences.saveCountryState(country)
        countryFlow.value = country
    }

    override fun getCountryFlow(): StateFlow<CountryShared?> = countryFlow
}

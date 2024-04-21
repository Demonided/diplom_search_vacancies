package ru.practicum.android.diploma.ui.filter.model

import ru.practicum.android.diploma.domain.filter.datashared.CountryShared
import ru.practicum.android.diploma.domain.filter.datashared.IndustriesShared
import ru.practicum.android.diploma.domain.filter.datashared.RegionShared
import ru.practicum.android.diploma.domain.filter.datashared.SalaryBooleanShared
import ru.practicum.android.diploma.domain.filter.datashared.SalaryTextShared

data class FilterAllViewFilterState(
    val country: CountryShared? = null,
    val region: RegionShared? = null,
    val industries: IndustriesShared? = null,
    val salaryText: SalaryTextShared? = null,
    val salaryBoolean: SalaryBooleanShared? = null,
)

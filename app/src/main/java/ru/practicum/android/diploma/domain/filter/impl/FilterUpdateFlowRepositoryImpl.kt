package ru.practicum.android.diploma.domain.filter.impl

import android.util.Log
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import ru.practicum.android.diploma.domain.filter.FilterRepositoryCountryFlow
import ru.practicum.android.diploma.domain.filter.FilterRepositoryIndustriesFlow
import ru.practicum.android.diploma.domain.filter.FilterRepositoryRegionFlow
import ru.practicum.android.diploma.domain.filter.FilterRepositorySalaryBooleanFlow
import ru.practicum.android.diploma.domain.filter.FilterRepositorySalaryTextFlow
import ru.practicum.android.diploma.domain.filter.FilterUpdateFlowRepository
import ru.practicum.android.diploma.domain.filter.datashared.CountryShared
import ru.practicum.android.diploma.domain.filter.datashared.IndustriesShared
import ru.practicum.android.diploma.domain.filter.datashared.RegionShared
import ru.practicum.android.diploma.domain.filter.datashared.SalaryBooleanShared
import ru.practicum.android.diploma.domain.filter.datashared.SalaryTextShared
import ru.practicum.android.diploma.domain.models.Filters

class FilterUpdateFlowRepositoryImpl(
    private val filterRepositoryCountryFlow: FilterRepositoryCountryFlow,
    private val filterRepositoryRegionFlow: FilterRepositoryRegionFlow,
    private val filterRepositoryIndustriesFlow: FilterRepositoryIndustriesFlow,
    private val filterRepositorySalaryTextFlow: FilterRepositorySalaryTextFlow,
    private val filterRepositorySalaryBooleanFlow: FilterRepositorySalaryBooleanFlow
) : FilterUpdateFlowRepository {

    private val flow = MutableSharedFlow<Filters>()

    override fun getFlow(): SharedFlow<Filters> = flow
    override suspend fun emitFlow() {
        val filters = makeFilters(
            filterRepositoryCountryFlow.getCountryFlow().value,
            filterRepositoryRegionFlow.getRegionFlow().value,
            filterRepositoryIndustriesFlow.getIndustriesFlow().value,
            filterRepositorySalaryTextFlow.getSalaryTextFlow().value,
            filterRepositorySalaryBooleanFlow.getSalaryBooleanFlow().value,
        )
        Log.d("emit", filters.toString())
        flow.emit(filters)
    }

    private fun makeFilters(
        county: CountryShared?,
        region: RegionShared?,
        industries: IndustriesShared?,
        salaryText: SalaryTextShared?,
        salaryBoolean: SalaryBooleanShared?
    ): Filters {
        var area = county?.countryId
        if (region != null) {
            area = region.regionId
        }
        return Filters(
            area = area,
            industry = industries?.industriesId,
            salary = salaryText?.salary,
            salaryBoolean = if (salaryBoolean != null) "true" else null,
        )
    }
}

package ru.practicum.android.diploma.domain.filter.impl

import android.util.Log
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import ru.practicum.android.diploma.domain.filter.FilterRepositoryCountryFlow
import ru.practicum.android.diploma.domain.filter.FilterRepositoryIndustriesFlow
import ru.practicum.android.diploma.domain.filter.FilterRepositoryRegionFlow
import ru.practicum.android.diploma.domain.filter.FilterRepositorySalaryBooleanFlow
import ru.practicum.android.diploma.domain.filter.FilterRepositorySalaryTextFlow
import ru.practicum.android.diploma.domain.filter.FiltersRepository
import ru.practicum.android.diploma.domain.filter.datashared.CountryShared
import ru.practicum.android.diploma.domain.filter.datashared.IndustriesShared
import ru.practicum.android.diploma.domain.filter.datashared.RegionShared
import ru.practicum.android.diploma.domain.filter.datashared.SalaryBooleanShared
import ru.practicum.android.diploma.domain.filter.datashared.SalaryTextShared
import ru.practicum.android.diploma.domain.models.Filters

class FiltersRepositoryImpl(
    private val filterRepositoryCountryFlow: FilterRepositoryCountryFlow,
    private val filterRepositoryRegionFlow: FilterRepositoryRegionFlow,
    private val filterRepositoryIndustriesFlow: FilterRepositoryIndustriesFlow,
    private val filterRepositorySalaryTextFlow: FilterRepositorySalaryTextFlow,
    private val filterRepositorySalaryBooleanFlow: FilterRepositorySalaryBooleanFlow
) : FiltersRepository {

    private var job: Job? = null
    private val filtersFlow: MutableStateFlow<Filters> = MutableStateFlow(Filters())

    override fun getFiltersFlow(): Flow<Filters> {
        filtersFlow.value = makeFilters(
            filterRepositoryCountryFlow.getCountryFlow().value,
            filterRepositoryRegionFlow.getRegionFlow().value,
            filterRepositoryIndustriesFlow.getIndustriesFlow().value,
            filterRepositorySalaryTextFlow.getSalaryTextFlow().value,
            filterRepositorySalaryBooleanFlow.getSalaryBooleanFlow().value,
        )
        Log.d("filters source", filtersFlow.value.toString())
        return filtersFlow
    }

    override fun getFilters(): Filters {
        return filtersFlow.value
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

    override fun cancelJob() {
        job?.cancel()
    }

}

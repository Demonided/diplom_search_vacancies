package ru.practicum.android.diploma.domain.filter.impl

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import ru.practicum.android.diploma.domain.filter.FilterRepositoryRegionFlow
import ru.practicum.android.diploma.domain.filter.datashared.RegionShared
import ru.practicum.android.diploma.domain.filter.storage.FiltersStorage

class FilterRepositoryRegionFlowImpl(
    private val sharedPreferences: FiltersStorage
) : FilterRepositoryRegionFlow {

    private val regionFlow: MutableStateFlow<RegionShared?> = MutableStateFlow(sharedPreferences.loadRegionState())
    override fun setRegionFlow(region: RegionShared?) {
        regionFlow.value = region
        sharedPreferences.saveRegionState(region)
    }

    override fun getRegionFlow(): StateFlow<RegionShared?> = regionFlow
}

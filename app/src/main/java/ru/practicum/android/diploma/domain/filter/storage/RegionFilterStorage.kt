package ru.practicum.android.diploma.domain.filter.storage

import ru.practicum.android.diploma.domain.filter.datashared.RegionShared

interface RegionFilterStorage {
    fun saveRegionState(region: RegionShared?)
    fun loadRegionState(): RegionShared?
}

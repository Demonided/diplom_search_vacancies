package ru.practicum.android.diploma.domain.filter.storage

import ru.practicum.android.diploma.domain.filter.datashared.IndustriesShared

interface IndustriesFilterStorage {
    fun saveIndustriesState(industries: IndustriesShared?)
    fun loadIndustriesState(): IndustriesShared?
}

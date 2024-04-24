package ru.practicum.android.diploma.domain.filter.impl

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import ru.practicum.android.diploma.domain.filter.FilterRepositoryIndustriesFlow
import ru.practicum.android.diploma.domain.filter.datashared.IndustriesShared
import ru.practicum.android.diploma.domain.filter.storage.IndustriesFilterStorage

class FilterRepositoryIndustriesFlowImpl(
    private val sharedPreferences: IndustriesFilterStorage
) : FilterRepositoryIndustriesFlow {

    private val industriesFlow: MutableStateFlow<IndustriesShared?> =
        MutableStateFlow(sharedPreferences.loadIndustriesState())

    override fun setIndustriesFlow(industries: IndustriesShared?) {
        industriesFlow.value = industries
        sharedPreferences.saveIndustriesState(industries)
    }

    override fun getIndustriesFlow(): StateFlow<IndustriesShared?> = industriesFlow
}

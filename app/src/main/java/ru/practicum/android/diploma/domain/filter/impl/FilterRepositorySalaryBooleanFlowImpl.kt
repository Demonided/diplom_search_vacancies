package ru.practicum.android.diploma.domain.filter.impl

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import ru.practicum.android.diploma.domain.filter.FilterRepositorySalaryBooleanFlow
import ru.practicum.android.diploma.domain.filter.datashared.SalaryBooleanShared
import ru.practicum.android.diploma.domain.filter.storage.SalaryBooleanFilterStorage

class FilterRepositorySalaryBooleanFlowImpl(
    private val sharedPreferences: SalaryBooleanFilterStorage
) : FilterRepositorySalaryBooleanFlow {

    private val salaryBooleanFlow: MutableStateFlow<SalaryBooleanShared?> =
        MutableStateFlow(sharedPreferences.loadSalaryBooleanState())

    override fun setSalaryBooleanFlow(salary: SalaryBooleanShared?) {
        salaryBooleanFlow.value = salary
        sharedPreferences.saveSalaryBooleanState(salary)
    }

    override fun getSalaryBooleanFlow(): StateFlow<SalaryBooleanShared?> = salaryBooleanFlow
}

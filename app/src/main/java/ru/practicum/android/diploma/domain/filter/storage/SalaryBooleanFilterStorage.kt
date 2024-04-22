package ru.practicum.android.diploma.domain.filter.storage

import ru.practicum.android.diploma.domain.filter.datashared.SalaryBooleanShared

interface SalaryBooleanFilterStorage {
    fun saveSalaryBooleanState(salary: SalaryBooleanShared?)
    fun loadSalaryBooleanState(): SalaryBooleanShared?
}

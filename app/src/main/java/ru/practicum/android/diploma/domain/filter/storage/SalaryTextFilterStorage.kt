package ru.practicum.android.diploma.domain.filter.storage

import ru.practicum.android.diploma.domain.filter.datashared.SalaryTextShared

interface SalaryTextFilterStorage {
    fun saveSalaryTextState(salary: SalaryTextShared?)
    fun loadSalaryTextState(): SalaryTextShared?
}

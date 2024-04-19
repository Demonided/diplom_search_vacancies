package ru.practicum.android.diploma.util

object SalaryFormatter {
    private const val SIZE_OF_MONEY_PART = 3
    fun format(salary: String) =
        salary
            .reversed()
            .chunked(SIZE_OF_MONEY_PART)
            .reversed()
            .joinToString(" ") {
                it.reversed()
            }
}

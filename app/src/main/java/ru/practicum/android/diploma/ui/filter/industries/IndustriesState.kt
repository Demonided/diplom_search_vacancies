package ru.practicum.android.diploma.ui.filter.industries

sealed interface IndustriesState {
    data object Loading : IndustriesState
    data class Empty(
        val message: Int
    ) : IndustriesState

    data class IndustriesList(
        val industries: List<ChildIndustryWithSelection>
    ) : IndustriesState

    data class SavedIndustry(
        val industry: ChildIndustryWithSelection
    ) : IndustriesState

    data class Error(
        val errorMessage: Int
    ) : IndustriesState
}

package ru.practicum.android.diploma.domain.country

data class Area(
    val id: String,
    val parentId: String?,
    val name: String?,
    val areas: List<Area>
)

package ru.practicum.android.diploma.data.converter

import ru.practicum.android.diploma.data.filter.country.dto.AreaDtoResponse
import ru.practicum.android.diploma.domain.country.Area
import ru.practicum.android.diploma.domain.country.Country

object AreaConverter {

    fun Area.mapToCountry(): Country = Country(
        id = id,
        parentId = parentId,
        name = name,
        areas = areas
    )

    fun AreaDtoResponse.mapToCountry(): Country = Country(
        id = id,
        parentId = parentId,
        name = name,
        areas = areas.mapToAreaList()
    )

    private fun AreaDtoResponse.mapToArea(): Area = Area(
        id = id,
        parentId = parentId,
        name = name,
        areas = areas.mapToAreaList()
    )

    private fun List<AreaDtoResponse>.mapToAreaList(): List<Area> = map { it.mapToArea() }

    fun List<AreaDtoResponse>.mapToCountryList(): List<Country> = map { it.mapToCountry() }
}

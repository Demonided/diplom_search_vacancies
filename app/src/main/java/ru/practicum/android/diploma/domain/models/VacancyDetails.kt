package ru.practicum.android.diploma.domain.models

import ru.practicum.android.diploma.domain.models.vacacy.Contacts
import ru.practicum.android.diploma.domain.models.vacacy.Employer
import ru.practicum.android.diploma.domain.models.vacacy.Salary

data class VacancyDetails(
    val id: String,
    val name: String,
    val salary: Salary?,
    val employer: Employer?,
    val city: String?,
    val fullAddress: String?,
    val areaName: String,
    val experience: String?,
    val employment: String?,
    val description: String,
    val contacts: Contacts?,
    val link: String,
    val keySkills: String
)

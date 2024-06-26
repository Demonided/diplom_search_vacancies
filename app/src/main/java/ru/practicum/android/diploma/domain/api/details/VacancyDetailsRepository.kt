package ru.practicum.android.diploma.domain.api.details

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.domain.models.VacancyDetails

interface VacancyDetailsRepository {

    suspend fun getVacancyDetails(id: String): Flow<VacancyDetails>
}

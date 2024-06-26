package ru.practicum.android.diploma.data.vacancies

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.practicum.android.diploma.data.network.NetworkClient
import ru.practicum.android.diploma.data.vacancies.dto.VacanciesSearchDtoResponse
import ru.practicum.android.diploma.data.vacancies.dto.VacanciesSearchRequest
import ru.practicum.android.diploma.data.vacancies.mapper.VacanciesFilterMapper
import ru.practicum.android.diploma.data.vacancies.mapper.VacanciesSearchMapper
import ru.practicum.android.diploma.data.vacancies.response.ResponseCodes
import ru.practicum.android.diploma.domain.api.search.VacanciesSearchRepository
import ru.practicum.android.diploma.domain.models.Filters
import ru.practicum.android.diploma.domain.models.vacacy.VacancyResponse

class VacanciesSearchRepositoryImpl(
    private val networkClient: NetworkClient
) : VacanciesSearchRepository {
    override fun getVacancies(
        query: String,
        page: Int,
        filters: Filters
    ): Flow<Pair<VacancyResponse?, String?>> =
        flow {
            val response =
                networkClient.doRequest(
                    VacanciesSearchRequest(
                        VacanciesFilterMapper.map(query, page, filters)
                    )
                )

            when (response.resultCode) {
                ResponseCodes.SUCCESS -> {
                    emit(Pair(VacanciesSearchMapper.map(response as VacanciesSearchDtoResponse), null))
                }

                ResponseCodes.NO_CONNECTION -> {
                    emit(Pair(null, ResponseCodes.NO_CONNECTION.code.toString()))
                }

                ResponseCodes.SERVER_ERROR -> {
                    emit(Pair(null, ResponseCodes.SERVER_ERROR.code.toString()))
                }

                else -> emit(Pair(null, "error"))
            }
        }

}

package ru.practicum.android.diploma.data.network

import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.QueryMap
import ru.practicum.android.diploma.BuildConfig
import ru.practicum.android.diploma.data.filter.country.dto.AreaDtoResponse
import ru.practicum.android.diploma.data.filter.industries.dto.ParentIndustriesResponse
import ru.practicum.android.diploma.data.vacancies.details.VacancyDetailDtoResponse
import ru.practicum.android.diploma.data.vacancies.dto.VacanciesSearchDtoResponse

interface SearchVacanciesApi {

    // Запрос списка вакансий
    @Headers(
        "Authorization: Bearer ${BuildConfig.HH_ACCESS_TOKEN}",
        "HH-User-Agent: practicum-android-diploma (makss.impeks@gmail.com)"
    )
    @GET("/vacancies")
    suspend fun getListVacancy(@QueryMap response: Map<String, String>): VacanciesSearchDtoResponse

    // Запрос детальной информации о вакансии
    @Headers(
        "Authorization: Bearer ${BuildConfig.HH_ACCESS_TOKEN}",
        "HH-User-Agent: practicum-android-diploma (makss.impeks@gmail.com)"
    )
    @GET("/vacancies/{vacancyId}")
    suspend fun getVacancyDetail(@Path("vacancyId") id: String): VacancyDetailDtoResponse

    // Запрос списка областей
    @Headers(
        "Authorization: Bearer ${BuildConfig.HH_ACCESS_TOKEN}",
        "HH-User-Agent: practicum-android-diploma (makss.impeks@gmail.com)"
    )
    @GET("/areas")
    suspend fun getAllAreas(): List<AreaDtoResponse>

    // Запрос списка регионов
    @Headers(
        "Authorization: Bearer ${BuildConfig.HH_ACCESS_TOKEN}",
        "HH-User-Agent: practicum-android-diploma (makss.impeks@gmail.com)"
    )
    @GET("/areas/{areas_id}")
    suspend fun getAreaId(@Path("areas_id") areasId: String): AreaDtoResponse

    // Запрос списка вакансий
    @Headers(
        "Authorization: Bearer ${BuildConfig.HH_ACCESS_TOKEN}",
        "HH-User-Agent: practicum-android-diploma (makss.impeks@gmail.com)"
    )
    @GET("/industries")
    suspend fun getAllIndustries(): List<ParentIndustriesResponse>
}

package ru.practicum.android.diploma.domain.country.impl

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.practicum.android.diploma.domain.country.Country
import ru.practicum.android.diploma.domain.country.CountryInteractor
import ru.practicum.android.diploma.domain.country.CountryRepository
import ru.practicum.android.diploma.util.ResourceContentSearch

class CountryInteractorImpl(
    val countryRepository: CountryRepository
) : CountryInteractor {

    override fun searchCountry(): Flow<Pair<List<Country>?, Int?>> {
        return countryRepository.searchCountry().map { resource ->
            when (resource) {
                is ResourceContentSearch.SuccessSearch -> Pair(resource.data, null)
                is ResourceContentSearch.ErrorSearch -> Pair(null, resource.message)
                is ResourceContentSearch.ServerErrorSearch -> Pair(null, SERVER_ERROR_CODE)
            }
        }
    }

    companion object {
        const val SERVER_ERROR_CODE = 500
    }
}

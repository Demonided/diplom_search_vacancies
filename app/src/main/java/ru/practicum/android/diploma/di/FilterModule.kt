package ru.practicum.android.diploma.di

import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.practicum.android.diploma.data.filter.storage.impl.CountryFilterStorageImpl
import ru.practicum.android.diploma.data.filter.storage.impl.IndustriesFilterStorageImpl
import ru.practicum.android.diploma.data.filter.storage.impl.RegionFilterStorageImpl
import ru.practicum.android.diploma.data.filter.storage.impl.SalaryBooleanFilterStorageImpl
import ru.practicum.android.diploma.data.filter.storage.impl.SalaryTextFilterStorageImpl
import ru.practicum.android.diploma.domain.filter.FilterRepositoryCountryFlow
import ru.practicum.android.diploma.domain.filter.FilterRepositoryIndustriesFlow
import ru.practicum.android.diploma.domain.filter.FilterRepositoryRegionFlow
import ru.practicum.android.diploma.domain.filter.FilterRepositorySalaryBooleanFlow
import ru.practicum.android.diploma.domain.filter.FilterRepositorySalaryTextFlow
import ru.practicum.android.diploma.domain.filter.FilterUpdateFlowRepository
import ru.practicum.android.diploma.domain.filter.impl.FilterRepositoryCountryFlowImpl
import ru.practicum.android.diploma.domain.filter.impl.FilterRepositoryIndustriesFlowImpl
import ru.practicum.android.diploma.domain.filter.impl.FilterRepositoryRegionFlowImpl
import ru.practicum.android.diploma.domain.filter.impl.FilterRepositorySalaryBooleanFlowImpl
import ru.practicum.android.diploma.domain.filter.impl.FilterRepositorySalaryTextFlowImpl
import ru.practicum.android.diploma.domain.filter.impl.FilterUpdateFlowRepositoryImpl
import ru.practicum.android.diploma.domain.filter.storage.CountryFilterStorage
import ru.practicum.android.diploma.domain.filter.storage.IndustriesFilterStorage
import ru.practicum.android.diploma.domain.filter.storage.RegionFilterStorage
import ru.practicum.android.diploma.domain.filter.storage.SalaryBooleanFilterStorage
import ru.practicum.android.diploma.domain.filter.storage.SalaryTextFilterStorage

val filterModule = module {

    single<FilterRepositoryCountryFlow> {
        FilterRepositoryCountryFlowImpl(get())
    }

    single<FilterRepositoryRegionFlow> {
        FilterRepositoryRegionFlowImpl(get())
    }

    single<FilterRepositoryIndustriesFlow> {
        FilterRepositoryIndustriesFlowImpl(get())
    }

    single<FilterRepositorySalaryTextFlow> {
        FilterRepositorySalaryTextFlowImpl(get())
    }

    single<FilterRepositorySalaryBooleanFlow> {
        FilterRepositorySalaryBooleanFlowImpl(get())
    }

    single<FilterUpdateFlowRepository> {
        FilterUpdateFlowRepositoryImpl(get(), get(), get(), get(), get())
    }

    single<CountryFilterStorage> {
        CountryFilterStorageImpl(get(named(SHARED_PREFS_NAME)), get())
    }

    single<IndustriesFilterStorage> {
        IndustriesFilterStorageImpl(get(named(SHARED_PREFS_NAME)), get())
    }

    single<RegionFilterStorage> {
        RegionFilterStorageImpl(get(named(SHARED_PREFS_NAME)), get())
    }

    single<SalaryTextFilterStorage> {
        SalaryTextFilterStorageImpl(get(named(SHARED_PREFS_NAME)), get())
    }

    single<SalaryBooleanFilterStorage> {
        SalaryBooleanFilterStorageImpl(get(named(SHARED_PREFS_NAME)), get())
    }
}

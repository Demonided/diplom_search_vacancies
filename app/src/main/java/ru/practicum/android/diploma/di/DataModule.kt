package ru.practicum.android.diploma.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.practicum.android.diploma.app.App
import ru.practicum.android.diploma.data.filter.storage.impl.FiltersLocalStorage
import ru.practicum.android.diploma.data.network.Endpoint
import ru.practicum.android.diploma.data.network.NetworkClient
import ru.practicum.android.diploma.data.network.RetrofitNetworkClient
import ru.practicum.android.diploma.data.network.SearchVacanciesApi
import ru.practicum.android.diploma.data.sharing.ExternalNavigatorImpl
import ru.practicum.android.diploma.domain.filter.storage.FiltersStorage
import ru.practicum.android.diploma.domain.sharing.ExternalNavigator

private const val FILTERS_PREFS = "FILTERS_PREFS"
const val SHARED_PREFS_NAME = "filtersPrefs"

val dataModule = module {

    single {
        androidApplication() as App
    }

    single<NetworkClient> {
        RetrofitNetworkClient(androidContext(), get())
    }

    single {
        Retrofit.Builder()
            .baseUrl(Endpoint.HeadHunter.baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SearchVacanciesApi::class.java)
    }

    single(qualifier = named(SHARED_PREFS_NAME)) {
        provideFiltersPreferences(androidApplication(), FILTERS_PREFS)
    }

    single<Gson> {
        Gson()
    }

    single<FiltersStorage> {
        FiltersLocalStorage(get(named(SHARED_PREFS_NAME)))
    }

    factory<ExternalNavigator> {
        ExternalNavigatorImpl(androidContext())
    }
}

private fun provideFiltersPreferences(app: Application, key: String): SharedPreferences =
    app.getSharedPreferences(key, Context.MODE_PRIVATE)

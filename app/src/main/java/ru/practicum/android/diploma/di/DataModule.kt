package ru.practicum.android.diploma.di

import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import ru.practicum.android.diploma.app.App
import ru.practicum.android.diploma.data.network.NetworkClient
import ru.practicum.android.diploma.data.network.RetrofitNetworkClient

val dataModule = module {

    single {
        androidApplication() as App
    }

    single<NetworkClient> {
        RetrofitNetworkClient(androidContext(), get())
    }
}

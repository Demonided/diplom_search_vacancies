package ru.practicum.android.diploma.di

import okhttp3.OkHttpClient
import org.koin.dsl.module
import ru.practicum.android.diploma.BuildConfig

val networkModule = module {

    single<OkHttpClient> {
        OkHttpClient
            .Builder()
            .addInterceptor { chain ->
                chain.run {
                    proceed(
                        request()
                            .newBuilder()
                            .addHeader("Authorization", "Bearer ${BuildConfig.HH_ACCESS_TOKEN}")
                            .addHeader("HH-User-Agent", "practicum-android-diploma (makss.impeks@gmail.com)")
                            .build()
                    )
                }
            }
            .build()
    }
}

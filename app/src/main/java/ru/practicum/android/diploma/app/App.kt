package ru.practicum.android.diploma.app

import android.app.Application
import com.markodevcic.peko.PermissionRequester
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import ru.practicum.android.diploma.di.dataModule
import ru.practicum.android.diploma.di.dbModule
import ru.practicum.android.diploma.di.filterModule
import ru.practicum.android.diploma.di.interactorModule
import ru.practicum.android.diploma.di.repositoryModule
import ru.practicum.android.diploma.di.viewModelModule

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(
                dataModule,
                dbModule,
                repositoryModule,
                interactorModule,
                viewModelModule,
                filterModule
            )
        }

        PermissionRequester.initialize(applicationContext)
    }
}

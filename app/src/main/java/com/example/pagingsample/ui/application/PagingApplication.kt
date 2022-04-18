package com.example.pagingsample.ui.application

import android.app.Application
import com.example.pagingsample.BuildConfig
import com.example.pagingsample.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.ksp.generated.defaultModule
//import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

class PagingApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        if(BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@PagingApplication)
            modules(defaultModule, apolloClientModule, appDatabaseModule,
                webServiceModule, databaseModule, pagingModule)
        }
    }

}
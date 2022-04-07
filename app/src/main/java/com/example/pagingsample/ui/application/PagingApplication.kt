package com.example.pagingsample.ui.application

import android.app.Application
import com.example.pagingsample.BuildConfig
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class PagingApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        if(BuildConfig.DEBUG){
            Timber.plant(Timber.DebugTree())
        }
    }

}
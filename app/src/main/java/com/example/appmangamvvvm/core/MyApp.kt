package com.example.appmangamvvvm.core

import android.app.Application
import com.example.appmangamvvvm.BuildConfig
import timber.log.Timber

class MyApp:Application() {
    override fun onCreate() {
        super.onCreate()
        if(BuildConfig.DEBUG){
            Timber.plant(Timber.DebugTree())
        }

        Timber.d("AppManga_TAG: onCreate: ")
    }
}
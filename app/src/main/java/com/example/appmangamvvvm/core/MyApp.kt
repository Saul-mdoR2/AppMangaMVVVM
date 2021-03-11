package com.example.appmangamvvvm.core

import android.app.Application
import com.example.appmangamvvvm.BuildConfig
import com.example.appmangamvvvm.di.presentationModule
import com.example.appmangamvvvm.di.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        Timber.d("AppManga_TAG: onCreate: ")

        startKoin {
            if (BuildConfig.DEBUG) androidLogger(Level.ERROR)
            androidContext(this@MyApp)
            modules(repositoryModule + presentationModule)
        }
    }
}
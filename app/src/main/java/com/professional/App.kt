package com.professional

import android.app.Application
import com.professional.di.koin.applicationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(modules = applicationModule)
            androidContext(this@App)
        }
    }
}
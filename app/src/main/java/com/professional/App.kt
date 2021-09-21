package com.professional

import com.professional.di.DaggerApplicationComponent
import com.professional.rxschedulers.SchedulersImpl
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class App : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<App> =
        DaggerApplicationComponent
            .builder().apply {
                withContext(applicationContext)
                withSchedulers(SchedulersImpl())
            }.build()
}
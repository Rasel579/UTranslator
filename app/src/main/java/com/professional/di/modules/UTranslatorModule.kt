package com.professional.di.modules

import com.professional.models.cloud.CloudImpl
import com.professional.models.cloud.CloudSource
import com.professional.models.repository.Repository
import com.professional.models.repository.RepositoryImpl
import com.professional.presentors.Interaction
import com.professional.presentors.MainInteraction
import com.professional.ui.mainfragment.MainFragment
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface UTranslatorModule {
    @ContributesAndroidInjector
    fun bindMainFragment(): MainFragment

    @Binds
    fun bindCloud(cloud: CloudImpl): CloudSource

    @Binds
    fun bindRepo(cloud: RepositoryImpl): Repository

    @Binds
    fun bindInteraction(interaction: MainInteraction): Interaction
}
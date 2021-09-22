package com.professional.di.modules

import com.professional.models.cloud.CloudImpl
import com.professional.models.cloud.CloudSource
import com.professional.models.repository.Repository
import com.professional.models.repository.RepositoryImpl
import com.professional.ui.mainfragment.MainFragment
import com.professional.utils.NetworkStatus
import com.professional.utils.NetworkStatusImpl
import com.professional.viewmodels.interactions.Interaction
import com.professional.viewmodels.interactions.MainInteraction
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

    @Binds
    fun bindNetworkStatus(networkStatus: NetworkStatusImpl): NetworkStatus
}
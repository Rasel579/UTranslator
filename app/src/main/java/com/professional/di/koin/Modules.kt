package com.professional.di.koin

import com.professional.models.cloud.CloudImpl
import com.professional.models.cloud.CloudSource
import com.professional.models.cloud.api.ServiceApi
import com.professional.models.repository.Repository
import com.professional.models.repository.RepositoryImpl
import com.professional.rxschedulers.Schedulers
import com.professional.rxschedulers.SchedulersImpl
import com.professional.utils.NetworkStatus
import com.professional.utils.NetworkStatusImpl
import com.professional.viewmodels.MainViewModel
import com.professional.viewmodels.base.BaseViewModel
import com.professional.viewmodels.interactions.Interaction
import com.professional.viewmodels.interactions.MainInteraction
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

private const val MAIN_VIEW_MODE = "Main View Model"
val applicationModule = module {
    single<NetworkStatus> { NetworkStatusImpl(androidContext()) }
    single<Schedulers> { SchedulersImpl() }
    single<CloudSource> { CloudImpl(get(), get()) }
    single<ServiceApi> { RetrofitModule.provideTranslatorApi() }
    single<Repository> { RepositoryImpl(get(), get()) }
    factory<Interaction> { MainInteraction(get(), get(), get()) }
    viewModel<BaseViewModel>(qualifier = named(MAIN_VIEW_MODE)) { MainViewModel(get(), get()) }
}
package com.professional.di.koin

import com.professional.models.cloud.CloudImpl
import com.professional.models.cloud.CloudSource
import com.professional.models.cloud.api.ServiceApi
import com.professional.models.repository.Repository
import com.professional.models.repository.RepositoryImpl
import com.professional.models.store.LocalSource
import com.professional.models.store.LocalSourceImpl
import com.professional.models.store.room.Database
import com.professional.schedulers.Schedulers
import com.professional.schedulers.SchedulersImpl
import com.professional.utils.NetworkStatus
import com.professional.utils.NetworkStatusImpl
import com.professional.viewmodels.DescriptionViewModel
import com.professional.viewmodels.FavoriteViewModel
import com.professional.viewmodels.HistoryViewModel
import com.professional.viewmodels.MainViewModel
import com.professional.viewmodels.interactions.DescriptionInteraction
import com.professional.viewmodels.interactions.FavoriteInteraction
import com.professional.viewmodels.interactions.Interaction
import com.professional.viewmodels.interactions.MainInteraction
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module


private const val DESCRIPTION_INTERACTION = "description interaction"
private const val MAIN_INTERACTION = "main interaction"
private const val FAVORITE_INTERACTION = "favorite interaction"
val applicationModule = module {
    single<NetworkStatus>(named<NetworkStatus>()) { NetworkStatusImpl(androidContext()) }
    single<Schedulers> { SchedulersImpl() }

    single<Database> { RoomDbModule.createDb(get()) }
    single<LocalSource>(named<LocalSource>()) { LocalSourceImpl(get<Database>().historyDao()) }
    single<CloudSource>(named<CloudSource>()) { CloudImpl(get(named<ServiceApi>())) }
    single<ServiceApi>(named<ServiceApi>()) { RetrofitModule.provideTranslatorApi() }
    single<Repository>(named<Repository>()) {
        RepositoryImpl(
            get(named<CloudSource>()),
            get(named<LocalSource>())
        )
    }

    factory<Interaction>(named(MAIN_INTERACTION)) {
        MainInteraction(
            get(named<Repository>()),
            get(named<NetworkStatus>())
        )
    }
    factory<Interaction>(named(DESCRIPTION_INTERACTION)) { DescriptionInteraction(get(named<Repository>())) }
    factory<Interaction>(named(FAVORITE_INTERACTION)) { FavoriteInteraction(get(named<Repository>())) }

    viewModel(named<MainViewModel>()) { MainViewModel(get(named(MAIN_INTERACTION))) }
    viewModel(named<HistoryViewModel>()) { HistoryViewModel(get(named(MAIN_INTERACTION))) }
    viewModel(named<DescriptionViewModel>()) {
        DescriptionViewModel(
            get(
                named(
                    DESCRIPTION_INTERACTION
                )
            )
        )
    }
    viewModel(named<FavoriteViewModel>()) { FavoriteViewModel(get(named(FAVORITE_INTERACTION))) }
}
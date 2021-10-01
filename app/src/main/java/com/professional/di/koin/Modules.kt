package com.professional.di.koin

import com.test_app.repository.cloud.CloudImpl
import com.test_app.repository.cloud.CloudSource
import com.test_app.repository.cloud.api.ServiceApi
import com.test_app.repository.Repository
import com.test_app.repository.RepositoryImpl
import com.test_app.repository.store.LocalSource
import com.test_app.repository.store.LocalSourceImpl
import com.test_app.repository.store.room.Database
import com.professional.schedulers.Schedulers
import com.professional.schedulers.SchedulersImpl
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
    single<com.test_app.utils.NetworkStatus>(named<com.test_app.utils.NetworkStatus>()) {
        com.test_app.utils.NetworkStatusImpl(
            androidContext()
        )
    }
    single<Schedulers> { SchedulersImpl() }

    single<com.test_app.repository.store.room.Database> { RoomDbModule.createDb(get()) }
    single<com.test_app.repository.store.LocalSource>(named<com.test_app.repository.store.LocalSource>()) {
        com.test_app.repository.store.LocalSourceImpl(
            get<com.test_app.repository.store.room.Database>().historyDao()
        )
    }
    single<com.test_app.repository.cloud.CloudSource>(named<com.test_app.repository.cloud.CloudSource>()) {
        com.test_app.repository.cloud.CloudImpl(
            get(named<com.test_app.repository.cloud.api.ServiceApi>())
        )
    }
    single<com.test_app.repository.cloud.api.ServiceApi>(named<com.test_app.repository.cloud.api.ServiceApi>()) { RetrofitModule.provideTranslatorApi() }
    single<com.test_app.repository.Repository>(named<com.test_app.repository.Repository>()) {
        com.test_app.repository.RepositoryImpl(
            get(named<com.test_app.repository.cloud.CloudSource>()),
            get(named<com.test_app.repository.store.LocalSource>())
        )
    }

    factory<Interaction>(named(MAIN_INTERACTION)) {
        MainInteraction(
            get(named<com.test_app.repository.Repository>()),
            get(named<com.test_app.utils.NetworkStatus>())
        )
    }
    factory<Interaction>(named(DESCRIPTION_INTERACTION)) { DescriptionInteraction(get(named<com.test_app.repository.Repository>())) }
    factory<Interaction>(named(FAVORITE_INTERACTION)) { FavoriteInteraction(get(named<com.test_app.repository.Repository>())) }

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
package com.professional.di.koin

import com.test_app.repository.cloud.CloudImpl
import com.test_app.repository.cloud.CloudSource
import com.test_app.repository.cloud.api.ServiceApi
import com.test_app.repository.Repository
import com.test_app.repository.RepositoryImpl
import com.test_app.repository.store.LocalSource
import com.test_app.repository.store.LocalSourceImpl
import com.test_app.repository.store.room.Database
import com.test_app.utils.schedulers.Schedulers
import com.test_app.utils.schedulers.SchedulersImpl
import com.test_app.descriptionfeature.viewmodel.DescriptionViewModel
import com.test_app.favoritefeature.viewmodel.FavoriteViewModel
import com.professional.viewmodels.MainViewModel
import com.test_app.descriptionfeature.interaction.DescriptionInteraction
import com.test_app.favoritefeature.interaction.FavoriteInteraction
import com.test_app.core.interaction.Interaction
import com.professional.viewmodels.interactions.MainInteraction
import com.test_app.historyfeature.viewmodel.HistoryViewModel
import com.test_app.utils.NetworkStatus
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module


private const val DESCRIPTION_INTERACTION = "description interaction"
private const val MAIN_INTERACTION = "main interaction"
private const val FAVORITE_INTERACTION = "favorite interaction"
val applicationModule = module {
    single<NetworkStatus>(named<NetworkStatus>()) {
        com.test_app.utils.NetworkStatusImpl(
            androidContext()
        )
    }
    single<Schedulers> { SchedulersImpl() }

    single { RoomDbModule.createDb(get()) }
    single<LocalSource>(named<LocalSource>()) {
        LocalSourceImpl(
            get<Database>().historyDao()
        )
    }
    single<CloudSource>(named<CloudSource>()) {
        CloudImpl(
            get(named<ServiceApi>())
        )
    }
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
    factory<Interaction>(named(DESCRIPTION_INTERACTION)) {
        DescriptionInteraction(
            get(named<Repository>())
        )
    }
    factory<Interaction>(named(FAVORITE_INTERACTION)) {
        FavoriteInteraction(
            get(named<Repository>())
        )
    }

    viewModel(named<MainViewModel>()) { MainViewModel(get(named(MAIN_INTERACTION))) }
    viewModel(named<HistoryViewModel>()) {
        HistoryViewModel(
            get(named(MAIN_INTERACTION))
        )
    }
    viewModel(named<DescriptionViewModel>()) {
        DescriptionViewModel(
            get(
                named(
                    DESCRIPTION_INTERACTION
                )
            )
        )
    }
    viewModel(named<FavoriteViewModel>()) {
        FavoriteViewModel(
            get(named(FAVORITE_INTERACTION))
        )
    }
}
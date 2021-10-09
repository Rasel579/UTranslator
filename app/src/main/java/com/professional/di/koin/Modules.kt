package com.professional.di.koin

import com.professional.ui.mainfragment.MainFragment
import com.professional.viewmodels.MainViewModel
import com.professional.viewmodels.interactions.MainInteraction
import com.test_app.core.interaction.Interaction
import com.test_app.descriptionfeature.interaction.DescriptionInteraction
import com.test_app.descriptionfeature.ui.DescriptionFragment
import com.test_app.descriptionfeature.viewmodel.DescriptionViewModel
import com.test_app.favoritefeature.interaction.FavoriteInteraction
import com.test_app.favoritefeature.ui.FavoriteFragment
import com.test_app.favoritefeature.viewmodel.FavoriteViewModel
import com.test_app.historyfeature.ui.HistoryFragment
import com.test_app.historyfeature.viewmodel.HistoryViewModel
import com.test_app.repository.Repository
import com.test_app.repository.RepositoryImpl
import com.test_app.repository.cloud.CloudImpl
import com.test_app.repository.cloud.CloudSource
import com.test_app.repository.cloud.api.ServiceApi
import com.test_app.repository.store.LocalSource
import com.test_app.repository.store.LocalSourceImpl
import com.test_app.repository.store.room.Database
import com.test_app.utils.network.NetworkStatus
import com.test_app.utils.network.NetworkStatusImpl
import com.test_app.utils.schedulers.Schedulers
import com.test_app.utils.schedulers.SchedulersImpl
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module


private const val MAIN_INTERACTION = "main interaction"
val applicationModule = module {
    single<NetworkStatus>(named<NetworkStatus>()) {
        NetworkStatusImpl(
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
}

val mainScope = module {
    scope<MainFragment> {
        scoped<Interaction> {
            MainInteraction(
                get(named<Repository>()),
                get(named<NetworkStatus>())
            )

        }
        viewModel(named<MainViewModel>()) { MainViewModel(get()) }
    }
}

val descriptionScope = module {
    scope<DescriptionFragment> {
        scoped<Interaction> {
            DescriptionInteraction(
                get(named<Repository>())
            )
        }
        viewModel(named<DescriptionViewModel>()) { DescriptionViewModel(get()) }

    }
}


val favoriteScope = module {
    scope<FavoriteFragment> {
        scoped<Interaction> {
            FavoriteInteraction(
                get(named<Repository>())
            )
        }
        viewModel(named<FavoriteViewModel>()) {
            FavoriteViewModel(get())
        }
    }
}

val historyScope = module {
    scope<HistoryFragment> {
        scoped<Interaction> {
            MainInteraction(
                get(named<Repository>()),
                get(named<NetworkStatus>())
            )
        }
        viewModel(named<HistoryViewModel>()) {
            HistoryViewModel(get())
        }
    }
}



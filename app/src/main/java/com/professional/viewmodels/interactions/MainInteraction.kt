package com.professional.viewmodels.interactions

import com.professional.models.AppState
import com.professional.models.repository.Repository
import com.professional.rxschedulers.Schedulers
import com.professional.utils.NetworkStatus
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class MainInteraction(
    private val repo: Repository,
    private val schedulers: Schedulers,
    private val networkStatus: NetworkStatus
) : Interaction {
    override fun getData(word: String): Single<out AppState> =
        networkStatus
            .onLineSingle()
            .flatMap { isOnline ->
                if (isOnline) {
                    repo
                        .getData(word)
                        .map {
                            AppState.Success(it)
                        }
                } else {
                    Single.fromCallable {
                        AppState.Error(
                            Throwable(ERROR_MSG)
                        )
                    }
                }
            }
            .subscribeOn(schedulers.io())

    companion object {
        private const val ERROR_MSG = "Error Internet connection"
    }
}


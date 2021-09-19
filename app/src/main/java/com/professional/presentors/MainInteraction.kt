package com.professional.presentors

import android.util.Log
import com.professional.models.AppState
import com.professional.models.repository.Repository
import com.professional.rxschedulers.Schedulers
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class MainInteraction @Inject constructor(
    private val repo: Repository,
    private val schedulers: Schedulers
) : Interaction {
    override fun getData(word: String): Single<out AppState> =
        repo
            .getData(word)
            .map {
                AppState.Success(it)
            }
            .subscribeOn(schedulers.io())
}
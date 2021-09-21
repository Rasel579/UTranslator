package com.professional.presentors

import com.professional.models.AppState
import com.professional.rxschedulers.Schedulers
import com.professional.views.MainView
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.plusAssign
import javax.inject.Inject

class MainPresenter @Inject constructor(
    private val interaction: Interaction,
    private val schedulers: Schedulers
) : Presenter<MainView, AppState> {
    private val disposable = CompositeDisposable()
    private var currentMainView: MainView? = null
    override fun attachView(mainView: MainView?) {
        if (currentMainView != mainView) {
            currentMainView = mainView
        }
    }

    override fun detachView(mainView: MainView?) {
        disposable.clear()
        if (currentMainView == mainView) {
            currentMainView = null
        }
    }

    override fun getData(word: String) {
        disposable += interaction
            .getData(word)
            .observeOn(schedulers.main())
            .doOnSubscribe {
                currentMainView?.renderData(AppState.Loading)
            }.subscribe(
                { currentMainView?.renderData(it)},
                { currentMainView?.renderData(AppState.Error(it))}
            )
    }

}
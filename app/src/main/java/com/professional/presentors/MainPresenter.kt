package com.professional.presentors

import android.util.Log
import com.professional.models.AppState
import com.professional.rxschedulers.Schedulers
import com.professional.views.View
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.plusAssign
import javax.inject.Inject

class MainPresenter @Inject constructor(
    private val interaction: Interaction,
    private val schedulers: Schedulers
) : Presenter<View, AppState> {
    private val disposable = CompositeDisposable()
    private var currentView: View? = null
    override fun attachView(view: View?) {
        if (currentView != view) {
            currentView = view
        }
    }

    override fun detachView(view: View?) {
        disposable.clear()
        if (currentView == view) {
            currentView = null
        }
    }

    override fun getData(word: String) {
        disposable += interaction
            .getData(word)
            .observeOn(schedulers.main())
            .doOnSubscribe {
                currentView?.renderData(AppState.Loading)
            }.subscribe(
                { currentView?.renderData(it)},
                { currentView?.renderData(AppState.Error(it))}
            )
    }

}
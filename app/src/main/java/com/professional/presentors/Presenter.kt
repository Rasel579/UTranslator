package com.professional.presentors

import com.professional.models.AppState
import com.professional.views.MainView

interface Presenter<T : MainView, V : AppState> {
    fun attachView(mainView: MainView?)
    fun detachView(mainView: MainView?)
    fun getData(word: String)
}
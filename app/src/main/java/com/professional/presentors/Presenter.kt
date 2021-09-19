package com.professional.presentors

import com.professional.models.AppState
import com.professional.views.View

interface Presenter<T : View, V : AppState> {
    fun attachView(view: View?)
    fun detachView(view: View?)
    fun getData(word: String)
}
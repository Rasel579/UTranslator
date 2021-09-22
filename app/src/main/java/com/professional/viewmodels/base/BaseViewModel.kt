package com.professional.viewmodels.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.professional.models.AppState
import io.reactivex.rxjava3.disposables.CompositeDisposable

abstract class BaseViewModel : ViewModel() {
    protected val customLiveData = MutableLiveData<AppState>()
    protected val disposable = CompositeDisposable()


    abstract fun getData(word: String)
    fun getLiveData() = customLiveData

    override fun onCleared() {
        disposable.clear()
        super.onCleared()
    }
}
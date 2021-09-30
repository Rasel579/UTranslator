package com.professional.viewmodels.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.professional.models.AppState
import kotlinx.coroutines.*

abstract class BaseViewModel : ViewModel() {
    protected val customLiveData = MutableLiveData<AppState>()
    protected val viewModelScopeCoroutine = CoroutineScope(
        Dispatchers.IO
                + SupervisorJob()
                + CoroutineExceptionHandler { _, throwable ->
            handleError(throwable)
        }
    )

    abstract fun handleError(throwable: Throwable): Any


    abstract fun getData(word: String)
    fun getLiveData() = customLiveData

    override fun onCleared() {
        super.onCleared()
        viewModelScopeCoroutine
            .coroutineContext
            .cancel()
    }
}
package com.test_app.historyfeature.viewmodel

import com.test_app.core.interaction.Interaction
import com.test_app.model.AppState
import kotlinx.coroutines.launch

class HistoryViewModel(
    private val interaction: Interaction
) : com.test_app.core.baseviewmodel.BaseViewModel() {

    override fun handleError(throwable: Throwable) {
        customLiveData.postValue(AppState.Error(throwable))
    }

    override fun getData(word: String) {
        TODO()
    }

    fun getData(){
        viewModelScopeCoroutine.launch {
            customLiveData.postValue(interaction.getHistoryData())
        }
    }

}
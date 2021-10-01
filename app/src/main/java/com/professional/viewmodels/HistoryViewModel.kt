package com.professional.viewmodels

import com.test_app.model.AppState
import com.professional.viewmodels.base.BaseViewModel
import com.professional.viewmodels.interactions.Interaction
import kotlinx.coroutines.launch

class HistoryViewModel(
    private val interaction: Interaction
) : BaseViewModel() {

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
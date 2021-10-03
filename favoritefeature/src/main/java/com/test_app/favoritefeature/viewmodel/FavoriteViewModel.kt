package com.test_app.favoritefeature.viewmodel

import com.test_app.core.baseviewmodel.BaseViewModel
import com.test_app.core.interaction.Interaction
import com.test_app.model.AppState
import kotlinx.coroutines.launch

class FavoriteViewModel(
    private val interaction: Interaction
) : BaseViewModel(){
    override fun handleError(throwable: Throwable) {
        customLiveData.postValue(AppState.Error(throwable))
    }

    override fun getData(word: String) {
        TODO("Not yet implemented")
    }

    fun getData(){
        viewModelScopeCoroutine.launch {
            customLiveData.postValue(interaction.getHistoryData())
        }
    }
}
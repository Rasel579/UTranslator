package com.professional.viewmodels

import com.professional.models.AppState
import com.professional.viewmodels.base.BaseViewModel
import com.professional.viewmodels.interactions.Interaction
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
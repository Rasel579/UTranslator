package com.professional.viewmodels

import com.test_app.model.AppState
import com.test_app.model.data.TranslationDataItem
import com.professional.viewmodels.base.BaseViewModel
import com.professional.viewmodels.interactions.Interaction
import kotlinx.coroutines.launch

class MainViewModel(
    private val interaction: Interaction,
) : BaseViewModel() {
    override fun handleError(throwable: Throwable) {
        customLiveData.postValue(AppState.Error(throwable))
    }

    override  fun getData(word: String) {
        customLiveData.value = AppState.Loading
       viewModelScopeCoroutine.launch {
           customLiveData.postValue(interaction.getData(word))
       }
    }

    fun saveToFavorite(item : TranslationDataItem){
        viewModelScopeCoroutine.launch {
            interaction.saveToFavorite(item)
        }
    }

}
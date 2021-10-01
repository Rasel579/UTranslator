package com.test_app.descriptionfeature.viewmodel

import com.test_app.core.interaction.Interaction
import com.test_app.model.AppState
import kotlinx.coroutines.launch

class DescriptionViewModel(
    private val interaction: Interaction
) : com.test_app.core.baseviewmodel.BaseViewModel() {
    override fun handleError(throwable: Throwable) {
        customLiveData.postValue(AppState.Error(throwable))
    }

    override fun getData(word: String) {
        viewModelScopeCoroutine.launch {
            customLiveData.postValue(interaction.getData(word))
        }
    }
}
package com.professional.viewmodels

import com.professional.models.AppState
import com.professional.rxschedulers.Schedulers
import com.professional.viewmodels.base.BaseViewModel
import com.professional.viewmodels.interactions.Interaction
import io.reactivex.rxjava3.kotlin.plusAssign

class MainViewModel(
    private val interaction: Interaction,
    private val schedulers: Schedulers
) : BaseViewModel() {
    override fun getData(word: String) {
        disposable += interaction
            .getData(word)
            .observeOn(schedulers.main())
            .doOnSubscribe {
                customLiveData.value = AppState.Loading
            }.subscribe(
                customLiveData::postValue
            ) {
                customLiveData.postValue(AppState.Error(it))
            }
    }

    override fun onCleared() {
        disposable.clear()
    }
}
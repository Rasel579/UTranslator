package com.professional.presentors

import com.professional.models.AppState
import io.reactivex.rxjava3.core.Single

interface Interaction {
    fun getData(word : String) : Single<out AppState>
}
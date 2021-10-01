package com.test_app.model

import com.test_app.model.data.TranslationDataItem
import com.test_app.model.data.entity.FavoriteDataEntity


sealed class AppState {
    data class Success(val data: List<TranslationDataItem>) : AppState()
    data class SuccessDescription(val data: TranslationDataItem) :
        AppState()

    data class SuccessFavorite(val data: List<FavoriteDataEntity>) : AppState()
    data class Error(val error: Throwable) : AppState()
    object Loading : AppState()
}

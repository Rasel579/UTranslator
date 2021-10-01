package com.professional.viewmodels.interactions

import com.test_app.model.AppState
import com.test_app.model.data.TranslationDataItem

interface Interaction {
    suspend fun getData(word: String): AppState
    suspend fun getHistoryData() : AppState
    suspend fun saveToFavorite(item: TranslationDataItem)
}
package com.professional.viewmodels.interactions

import com.professional.models.AppState
import com.professional.models.data.TranslationDataItem

interface Interaction {
    suspend fun getData(word: String): AppState
    suspend fun getHistoryData() : AppState
    suspend fun saveToFavorite(item: TranslationDataItem)
}
package com.professional.models.repository

import com.professional.models.data.TranslationDataItem
import com.professional.models.store.room.entity.FavoriteDataEntity

interface Repository {
    suspend fun getFromCloudData(word : String) : List<TranslationDataItem>
    suspend fun getHistoryData() : List<TranslationDataItem>
    suspend fun saveToDatabase(word: List<TranslationDataItem>)
    suspend fun getDataByWordFromDb(word : String): TranslationDataItem
    suspend fun getFavorite() : List<FavoriteDataEntity>
    suspend fun insertFavorite(word: TranslationDataItem)
}
package com.test_app.repository

import com.test_app.model.data.TranslationDataItem
import com.test_app.model.data.entity.FavoriteDataEntity


interface Repository {
    suspend fun getFromCloudData(word : String) : List<TranslationDataItem>
    suspend fun getHistoryData() : List<TranslationDataItem>
    suspend fun saveToDatabase(word: List<TranslationDataItem>)
    suspend fun getDataByWordFromDb(word : String): TranslationDataItem
    suspend fun getFavorite() : List<FavoriteDataEntity>
    suspend fun insertFavorite(word: TranslationDataItem)
}
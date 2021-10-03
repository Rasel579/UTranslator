package com.test_app.repository.store

import com.test_app.model.data.TranslationDataItem
import com.test_app.model.data.entity.FavoriteDataEntity

interface LocalSource {
    suspend fun getData() : List<TranslationDataItem>
    suspend fun save(word: TranslationDataItem)
    suspend fun save(word: List<TranslationDataItem>)
    suspend fun getDataByWord(word : String) : TranslationDataItem
    suspend fun insertFavorite(word: TranslationDataItem)
    suspend fun getFavorite(): List<FavoriteDataEntity>
}
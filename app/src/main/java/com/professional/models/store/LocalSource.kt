package com.professional.models.store

import com.professional.models.data.TranslationDataItem
import com.professional.models.store.room.entity.FavoriteDataEntity

interface LocalSource {
    suspend fun getData() : List<TranslationDataItem>
    suspend fun save(word: TranslationDataItem)
    suspend fun save(word: List<TranslationDataItem>)
    suspend fun getDataByWord(word : String) : TranslationDataItem
    suspend fun insertFavorite(word: TranslationDataItem)
    suspend fun getFavorite(): List<FavoriteDataEntity>
}
package com.professional.models.repository

import com.professional.models.cloud.CloudSource
import com.professional.models.data.TranslationDataItem
import com.professional.models.store.LocalSource
import com.professional.models.store.room.entity.FavoriteDataEntity

class RepositoryImpl(
    private val cloud: CloudSource,
    private val localStorage: LocalSource
) : Repository {
    override suspend fun getFromCloudData(word: String): List<TranslationDataItem> =
        cloud.getData(word)

    override suspend fun getHistoryData(): List<TranslationDataItem> =
        localStorage.getData()

    override suspend fun saveToDatabase(word: List<TranslationDataItem>) =
        localStorage.save(word)

    override suspend fun getDataByWordFromDb(word: String): TranslationDataItem =
        localStorage.getDataByWord(word)

    override suspend fun getFavorite(): List<FavoriteDataEntity> =
        localStorage.getFavorite()

    override suspend fun insertFavorite(word: TranslationDataItem) =
        localStorage.insertFavorite(word)

}
package com.test_app.repository

import com.test_app.model.data.TranslationDataItem
import com.test_app.repository.cloud.CloudSource
import com.test_app.repository.store.LocalSource
import com.test_app.model.data.entity.FavoriteDataEntity


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
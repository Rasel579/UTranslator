package com.test_app.repository.store

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.test_app.model.data.TranslationDataItem
import com.test_app.model.data.entity.FavoriteDataEntity
import com.test_app.model.data.entity.TranslationDataItemEntity
import com.test_app.repository.store.room.HistoryDao

class LocalSourceImpl(
    private val localDatabase: HistoryDao
) : LocalSource {
    override suspend fun getData(): List<TranslationDataItem> =
        localDatabase.getAll()
            .map { item ->
                switchMapFromEntityToData(item)
            }


    override suspend fun save(word: TranslationDataItem) {
        val data = switchMapFromDataToEntity(word)
        localDatabase.insert(data)
    }

    override suspend fun save(word: List<TranslationDataItem>) {
        val data = word.flatMap {
            listOf<TranslationDataItemEntity>(switchMapFromDataToEntity(it))
        }
        localDatabase.insertAll(data)
    }

    override suspend fun getDataByWord(word: String): TranslationDataItem =
        switchMapFromEntityToData(localDatabase.getDataByWord(word))

    override suspend fun insertFavorite(word: TranslationDataItem) =
        localDatabase.insertFavorite(
            FavoriteDataEntity(
                word = word.text,
                translation = word.meanings.first().translation.text
            )
        )

    override suspend fun getFavorite(): List<FavoriteDataEntity> =
        localDatabase.getFavoriteAll()

    private fun switchMapFromDataToEntity(translationDataItem: TranslationDataItem) =
        TranslationDataItemEntity(
            id = translationDataItem.id,
            meanings = convertToJson(translationDataItem.meanings),
            text = translationDataItem.text
        )

    private fun switchMapFromEntityToData(translationDataItemEntity: TranslationDataItemEntity) =
        TranslationDataItem(
            id = translationDataItemEntity.id,
            convertFromJson(translationDataItemEntity.meanings),
            translationDataItemEntity.text
        )

    private fun convertToJson(meaning: List<com.test_app.model.data.Meaning>): String =
        Gson().toJson(meaning, object : TypeToken<List<com.test_app.model.data.Meaning>>() {}.type)

    private fun convertFromJson(meaning: String): List<com.test_app.model.data.Meaning> =
        Gson().fromJson(
            meaning,
            object : TypeToken<List<com.test_app.model.data.Meaning>>() {}.type
        )


}

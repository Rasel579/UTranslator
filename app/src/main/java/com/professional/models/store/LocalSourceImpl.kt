package com.professional.models.store

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.professional.models.data.Meaning
import com.professional.models.data.TranslationDataItem
import com.professional.models.store.room.HistoryDao
import com.professional.models.store.room.entity.FavoriteDataEntity
import com.professional.models.store.room.entity.TranslationDataItemEntity

class LocalSourceImpl(
    private val localDatabase: HistoryDao
) : LocalSource {
    override suspend fun getData(): List<TranslationDataItem> {
        val data = localDatabase.getAll().let { item ->
            item.flatMap { item ->
                listOf<TranslationDataItem>(
                    switchMapFromEntityToData(item)
                )
            }
        }
        return data
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

    private fun convertToJson(meaning: List<Meaning>): String =
        Gson().toJson(meaning, object : TypeToken<List<Meaning>>() {}.type)

    private fun convertFromJson(meaning: String): List<Meaning> =
        Gson().fromJson<List<Meaning>>(meaning, object : TypeToken<List<Meaning>>() {}.type)


}

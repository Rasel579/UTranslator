package com.professional.models.store.room

import androidx.room.*
import com.professional.models.store.room.entity.FavoriteDataEntity
import com.professional.models.store.room.entity.TranslationDataItemEntity

@Dao
interface HistoryDao {
    @Query("SELECT * FROM  TranslationDataItemEntity")
    suspend fun getAll(): List<TranslationDataItemEntity>

    @Query("SELECT * FROM FavoriteDataEntity")
    suspend fun getFavoriteAll(): List<FavoriteDataEntity>

    @Query("SELECT * FROM TranslationDataItemEntity WHERE text LIKE :word ")
    suspend fun getDataByWord(word: String): TranslationDataItemEntity

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(word: TranslationDataItemEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertFavorite(word: FavoriteDataEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(word: List<TranslationDataItemEntity>)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun update(word: TranslationDataItemEntity)

    @Delete
    suspend fun delete(word: TranslationDataItemEntity)

}
package com.professional.models.store.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.professional.models.store.room.entity.FavoriteDataEntity
import com.professional.models.store.room.entity.TranslationDataItemEntity

@Database(entities = [TranslationDataItemEntity::class,
    FavoriteDataEntity::class], exportSchema = false, version = 2)
abstract class Database : RoomDatabase() {
    abstract fun historyDao(): HistoryDao
}
package com.test_app.repository.store.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.test_app.model.data.entity.FavoriteDataEntity
import com.test_app.model.data.entity.TranslationDataItemEntity

@Database(entities = [TranslationDataItemEntity::class,
    FavoriteDataEntity::class], exportSchema = false, version = 2)
abstract class Database : RoomDatabase() {
    abstract fun historyDao(): HistoryDao
}
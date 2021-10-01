package com.professional.di.koin

import android.content.Context
import androidx.room.Room
import com.test_app.repository.store.room.Database

object RoomDbModule {
    private const val NAME_DB = "TranslationDB"
    fun createDb(context: Context): com.test_app.repository.store.room.Database =
        Room
            .databaseBuilder(context, com.test_app.repository.store.room.Database::class.java, NAME_DB)
            .build()

}
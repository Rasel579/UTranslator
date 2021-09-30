package com.professional.di.koin

import android.content.Context
import androidx.room.Room
import com.professional.models.store.room.Database

object RoomDbModule {
    private const val NAME_DB = "TranslationDB"
    fun createDb(context: Context): Database =
        Room
            .databaseBuilder(context, Database::class.java, NAME_DB)
            .build()

}
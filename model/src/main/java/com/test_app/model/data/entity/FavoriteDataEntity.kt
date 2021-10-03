package com.test_app.model.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FavoriteDataEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val word: String,
    val translation: String
)

package com.test_app.model.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TranslationDataItemEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val meanings: String,
    val text: String
)
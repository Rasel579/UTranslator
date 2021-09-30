package com.professional.models.store.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TranslationDataItemEntity(
    @field: PrimaryKey(autoGenerate = true)
    val id: Int,
    val meanings: String,
    val text: String
)
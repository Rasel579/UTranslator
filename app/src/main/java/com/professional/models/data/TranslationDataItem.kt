package com.professional.models.data

import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class TranslationDataItem(
    @field: PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    val id: Int,
    @SerializedName("meanings")
    val meanings: List<Meaning>,
    @SerializedName("text")
    val text: String
)
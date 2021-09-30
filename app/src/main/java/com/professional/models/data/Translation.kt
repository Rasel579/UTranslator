package com.professional.models.data

import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class Translation(
    @PrimaryKey(autoGenerate = true)
    val translationId: Int,
    @SerializedName("note")
    val note: String,
    @SerializedName("text")
    val text: String
)
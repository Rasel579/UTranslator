package com.professional.models.data

import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class Meaning(
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    val meaningId: Int,
    @SerializedName("imageUrl")
    val imageUrl: String,
    @SerializedName("partOfSpeechCode")
    val partOfSpeechCode: String,
    @SerializedName("previewUrl")
    val previewUrl: String,
    @SerializedName("soundUrl")
    val soundUrl: String,
    @SerializedName("transcription")
    val transcription: String,
    @SerializedName("translation")
    val translation: Translation
)
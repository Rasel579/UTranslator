package com.test_app.model.data

import com.google.gson.annotations.SerializedName

data class TranslationDataItem(
    @SerializedName("id")
    val id: Int,
    @SerializedName("meanings")
    val meanings: List<Meaning>,
    @SerializedName("text")
    val text: String
)
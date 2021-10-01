package com.test_app.model.data

import com.google.gson.annotations.SerializedName

data class Translation(
    val translationId: Int,
    @SerializedName("note")
    val note: String,
    @SerializedName("text")
    val text: String
)
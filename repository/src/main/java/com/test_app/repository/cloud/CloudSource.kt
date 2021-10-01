package com.test_app.repository.cloud

interface CloudSource {
    suspend fun getData(word : String) : List<com.test_app.model.data.TranslationDataItem>
}
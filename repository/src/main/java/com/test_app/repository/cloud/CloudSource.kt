package com.test_app.repository.cloud

import com.test_app.model.data.TranslationDataItem

interface CloudSource {
    suspend fun getData(word : String) : List<TranslationDataItem>
}
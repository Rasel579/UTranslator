package com.test_app.repository.cloud

import com.test_app.model.data.TranslationDataItem
import com.test_app.repository.cloud.api.ServiceApi

class CloudImpl(
    private val api: ServiceApi,
) : CloudSource {
    override suspend fun getData(word: String): List<TranslationDataItem> =
        api.getTranslation(word)
}
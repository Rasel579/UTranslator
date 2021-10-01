package com.test_app.repository.cloud

import com.test_app.repository.cloud.api.ServiceApi

class CloudImpl(
    private val api: ServiceApi,
) : CloudSource {
    override suspend fun getData(word: String): List<com.test_app.model.data.TranslationDataItem> =
        api.getTranslation(word)
}
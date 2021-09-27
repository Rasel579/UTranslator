package com.professional.models.cloud

import com.professional.models.cloud.api.ServiceApi
import com.professional.models.data.TranslationDataItem

class CloudImpl(
    private val api: ServiceApi,
) : CloudSource{
    override suspend fun getData(word: String): List<TranslationDataItem> =
        api.getTranslation(word)
}
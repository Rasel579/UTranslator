package com.professional.models.repository

import com.professional.models.cloud.CloudSource
import com.professional.models.data.TranslationDataItem

class RepositoryImpl(
    private val cloud: CloudSource
) : Repository {
    override suspend fun getData(word: String): List<TranslationDataItem> =
        cloud.getData(word)
}
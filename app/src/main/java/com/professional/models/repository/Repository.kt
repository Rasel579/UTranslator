package com.professional.models.repository

import com.professional.models.data.TranslationDataItem

interface Repository {
    suspend fun getData(word : String) : List<TranslationDataItem>
}
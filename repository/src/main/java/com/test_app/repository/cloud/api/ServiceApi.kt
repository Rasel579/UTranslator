package com.test_app.repository.cloud.api

import com.test_app.model.data.TranslationDataItem
import retrofit2.http.GET
import retrofit2.http.Query

interface ServiceApi {
    @GET("api/public/v1/words/search")
    suspend fun getTranslation(@Query("search") word: String): List<TranslationDataItem>
}
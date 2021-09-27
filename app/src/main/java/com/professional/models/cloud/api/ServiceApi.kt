package com.professional.models.cloud.api

import com.professional.models.data.TranslationDataItem
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ServiceApi {
    @GET("api/public/v1/words/search")
    suspend fun getTranslation(@Query("search") word: String): List<TranslationDataItem>
}
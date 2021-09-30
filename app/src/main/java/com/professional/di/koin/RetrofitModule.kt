package com.professional.di.koin

import com.google.gson.GsonBuilder
import com.professional.BuildConfig
import com.professional.models.cloud.api.ServiceApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitModule {
    private const val BASE_URL = "https://dictionary.skyeng.ru/"
    private val gsonBuilder = GsonBuilder().create()

    fun provideTranslatorApi(): ServiceApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(
            OkHttpClient()
                .newBuilder()
                .apply {
                    if (BuildConfig.DEBUG) {
                        addInterceptor(HttpLoggingInterceptor()
                            .apply {
                                level = HttpLoggingInterceptor.Level.BODY
                            })
                    }
                }.build()
        )
        .addConverterFactory(GsonConverterFactory.create(gsonBuilder))
        .build()
        .create(ServiceApi::class.java)

}
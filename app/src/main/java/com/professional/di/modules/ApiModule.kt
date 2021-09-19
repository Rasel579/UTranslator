package com.professional.di.modules

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.professional.models.cloud.api.ServiceApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class ApiModule {
   private val baseUrl = "https://dictionary.skyeng.ru/"
   private val gsonBuilder = GsonBuilder().create()
   @Provides
   fun provideTranslatorApi(): ServiceApi = Retrofit.Builder()
       .baseUrl(baseUrl)
       .client(OkHttpClient()
           .newBuilder()
           .addInterceptor(
               HttpLoggingInterceptor().apply {
                   level = HttpLoggingInterceptor.Level.BODY
               }
           ).build()
       ).addCallAdapterFactory(RxJava3CallAdapterFactory.create())
       .addConverterFactory(GsonConverterFactory.create(gsonBuilder))
       .build()
       .create(ServiceApi::class.java)
}
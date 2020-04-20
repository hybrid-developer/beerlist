package com.zavaitar.core.network.api

import com.zavaitar.core.network.interceptors.AuthorisationInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
object ApiModule {

    @Singleton
    @JvmStatic
    @Provides
    @TwitterFeedNetworkQualifier
    fun okHttpClient(
        apiConfig: TwitterFeedApiConfig,
        builder: OkHttpClient.Builder
    ): OkHttpClient = builder
        .apply {
            addInterceptor(AuthorisationInterceptor())
            apiConfig.httpInterceptors.forEach { addNetworkInterceptor(it) }
        }
        .build()

    @JvmStatic
    @Provides
    @TwitterFeedNetworkQualifier
    fun retrofit(
        @TwitterFeedNetworkQualifier okHttpClient: OkHttpClient,
        builder: Retrofit.Builder
    ): Retrofit = builder.baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()

    @JvmStatic
    @Provides
    fun twitterFeedApi(@TwitterFeedNetworkQualifier retrofit: Retrofit): LawyerFeedApi = retrofit.create(LawyerFeedApi::class.java)

    private const val BASE_URL = "http://www.zavaitar.com"
}

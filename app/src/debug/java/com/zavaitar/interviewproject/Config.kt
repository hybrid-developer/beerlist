package com.zavaitar.interviewproject

import com.facebook.stetho.Stetho
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.zavaitar.core.network.api.TwitterFeedApiConfig
import com.zavaitar.interviewproject.di.ApplicationComponent
import com.zavaitar.interviewproject.di.ConfigContract
import okhttp3.logging.HttpLoggingInterceptor

object Config : ConfigContract {

    private val interceptors by lazy {
        listOf(
            HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY },
            StethoInterceptor()
        )
    }

    override fun twitterFeedApiConfig() =  TwitterFeedApiConfig(interceptors)

    override fun applicationInjector(application: MainApplication): ApplicationComponent {
        Stetho.initializeWithDefaults(application)
        return super.applicationInjector(application)
    }
}

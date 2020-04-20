package com.zavaitar.interviewproject.di

import android.content.Context
import android.net.ConnectivityManager
import com.zavaitar.core.network.NetworkModule
import com.zavaitar.core.network.NetworkState
import com.zavaitar.core.network.NetworkStateImpl
import com.zavaitar.core.network.api.TwitterFeedApiConfig
import com.zavaitar.core.network.module.TwitterFeedApiModule
import com.zavaitar.interviewproject.MainApplication

interface ConfigContract {

    fun networkState(manager: ConnectivityManager): NetworkState = NetworkStateImpl(manager)

    fun twitterFeedApiConfig() = TwitterFeedApiConfig()

    fun applicationInjector(application: MainApplication): ApplicationComponent {
        val networkInfo = (application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager)

        return DaggerApplicationComponent.builder()
            .context(application)
            .networkModule(NetworkModule(networkState(networkInfo)))
            .twitterFeedApiModule(TwitterFeedApiModule(twitterFeedApiConfig()))
            .build()
    }
}
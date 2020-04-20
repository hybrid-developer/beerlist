package com.zavaitar.core.network.module

import com.zavaitar.core.network.api.ApiModule
import com.zavaitar.core.network.api.TwitterFeedApiConfig
import dagger.Module
import dagger.Provides

@Module(includes = [ApiModule::class])
class TwitterFeedApiModule(
    private val twitterFeedApiConfig: TwitterFeedApiConfig
) {

    @Provides
    fun twitterFeedApiConfig() = twitterFeedApiConfig
}
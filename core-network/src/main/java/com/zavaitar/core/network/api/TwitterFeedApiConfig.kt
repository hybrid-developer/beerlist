package com.zavaitar.core.network.api

import okhttp3.Interceptor

class TwitterFeedApiConfig(
    val httpInterceptors: List<Interceptor> = emptyList()
)
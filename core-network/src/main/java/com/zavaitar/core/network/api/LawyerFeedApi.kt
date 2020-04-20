package com.zavaitar.core.network.api

import com.zavaitar.core.network.model.LawyerFeedResponse
import retrofit2.http.GET

interface LawyerFeedApi {

    @GET("/v1/lawyers.json")
    suspend fun getLawyerFeed(): List<LawyerFeedResponse>
}
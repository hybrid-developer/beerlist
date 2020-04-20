package com.zavaitar.feature.feed

import androidx.annotation.VisibleForTesting
import com.zavaitar.core.network.api.LawyerFeedApi
import com.zavaitar.core.network.model.LawyerFeedResponse
import com.zavaitar.feature.feed.model.LawyerFeed
import com.zavaitar.feature.feed.model.convertToFeed
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Provider

interface FeedManager {
    suspend fun getLawyerFeed(): List<LawyerFeed>
}

internal class FeedManagerImpl @Inject constructor(
    private val feedApiProvider: Provider<LawyerFeedApi>
) : FeedManager {

    //This function was unit tested in FeedUseCase
    override suspend fun getLawyerFeed(): List<LawyerFeed> {
        return convertToLawyerFeed(
            feedApiProvider.get().getLawyerFeed()
        )
    }

    @VisibleForTesting
    internal fun convertToLawyerFeed(response: List<LawyerFeedResponse>): List<LawyerFeed> {
        return Single.just(response)
            .map { items ->
                items.map { lawyerFeedResponse -> lawyerFeedResponse.convertToFeed() }
            }.blockingGet()
    }
}

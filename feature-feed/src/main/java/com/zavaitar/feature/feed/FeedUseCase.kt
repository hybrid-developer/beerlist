package com.zavaitar.feature.feed

import androidx.annotation.VisibleForTesting
import com.zavaitar.core.network.NetworkState
import com.zavaitar.core.viewmodel.Result
import com.zavaitar.feature.feed.model.LawyerFeed
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface FeedUseCase {
    suspend fun getLawyerFeed(): Result<List<LawyerFeed>>
}

internal class FeedUseCaseImpl @Inject constructor(
    private val networkState: NetworkState,
    private val feedManager: FeedManager
) : FeedUseCase {

    // This function has been united tested in FeedViewModelTest
    override suspend fun getLawyerFeed(): Result<List<LawyerFeed>> = withContext(Dispatchers.IO){
        whenConnected {
            mapResults(feedManager.getLawyerFeed())
        }
    }

    @VisibleForTesting
    internal fun mapResults(response: List<LawyerFeed>) : Result<List<LawyerFeed>> {
        return try {
            Result.Success(response)
        } catch (ex: Exception) {
            Result.Error("Lawyer Feed Error !!")
        }
    }

    private suspend fun <T> whenConnected(apiCall: suspend () -> Result<T>): Result<T> {
        return try {
            if (networkState.isNetworkConnected()) {
                apiCall()
            } else {
                Result.Error(Result.NO_NETWORK)
            }
        } catch (e: Exception) {
            Result.Error()
        }
    }
}
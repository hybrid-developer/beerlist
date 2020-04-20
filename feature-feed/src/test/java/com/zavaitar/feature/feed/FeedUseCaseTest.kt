package com.zavaitar.feature.feed

import com.zavaitar.core.network.NetworkState
import com.zavaitar.core.viewmodel.Result
import com.zavaitar.feature.feed.model.LawyerFeed
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class FeedUseCaseTest {

    @Mock
    lateinit var feedManager: FeedManager

    @Mock
    lateinit var networkState: NetworkState

    private lateinit var feedUseCase: FeedUseCaseImpl

    @Before
    fun setup() {
        feedUseCase = FeedUseCaseImpl(networkState, feedManager)
    }

    @Test
    fun `return success result when you get the response as expected `() {
        val list = lawyerFeedData()

        val result = feedUseCase.mapResults(list)

        assertTrue { result is Result.Success<List<LawyerFeed>> }
        assertEquals((result as Result.Success).data.size, 6)
    }

    @Test
    fun `return success result with an empty list when you get empty data`() {
        val list = emptyList<LawyerFeed>()

        val result = feedUseCase.mapResults(list)

        assertTrue { result is Result.Success<List<LawyerFeed>> }
        assertEquals((result as Result.Success).data.size, 0)
    }

    private fun lawyerFeedData(): List<LawyerFeed> {
        val list = mutableListOf<LawyerFeed>()
        for (index in 0..5) {
            list.add(
                LawyerFeed(
                    id = ID,
                    noOfReviews = NUMBER_OF_REVIEWS,
                    rating = RATING,
                    firstName = "$FIRST_NAME$index",
                    description = "$DESCRIPTION$index"
                )
            )
        }
        return list
    }

    private companion object {
        private const val RATING = 5.0
        private const val NUMBER_OF_REVIEWS = 10
        private const val ID = 123
        private const val FIRST_NAME = "First Name"
        private const val DESCRIPTION = "Description"
    }
}
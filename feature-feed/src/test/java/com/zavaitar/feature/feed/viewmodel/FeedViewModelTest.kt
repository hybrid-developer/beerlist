package com.zavaitar.feature.feed.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.never
import com.nhaarman.mockitokotlin2.stub
import com.nhaarman.mockitokotlin2.verify
import com.zavaitar.core.test.rxutils.coroutines.MainCoroutineRule
import com.zavaitar.core.test.rxutils.livedata.getData
import com.zavaitar.core.viewmodel.Result
import com.zavaitar.feature.feed.FeedUseCase
import com.zavaitar.feature.feed.R
import com.zavaitar.feature.feed.model.LawyerFeed
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import kotlin.math.absoluteValue
import kotlin.test.*

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class FeedViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var feedUseCase: FeedUseCase
    @Mock
    lateinit var observerSingleEvent: Observer<Void>

    private lateinit var feedViewModel: FeedViewModel

    @Before
    fun setup() {
        feedViewModel = FeedViewModel(feedUseCase)
    }

    @Test
    fun `display list empty error message when loading lawyer feed`() {
        feedUseCase.stub {
            on { runBlocking { getLawyerFeed() } } doReturn Result.Success(emptyList())
        }

        feedViewModel.dataLoadingStopEvent.observeForever(observerSingleEvent)
        mainCoroutineRule.pauseDispatcher()

        feedViewModel.loadLawyerFeed()

        verify(observerSingleEvent, never()).onChanged(null)

        mainCoroutineRule.resumeDispatcher()

        assertTrue(feedViewModel.errorMessageDisplayStatusEvent.getData())
        assertEquals(
            feedViewModel.errorScreenEvent.getData().absoluteValue,
            R.string.feed_data_not_received
        )
        verify(observerSingleEvent).onChanged(null)
    }

    @Test
    fun `display actual list when api returns the list full lawyer feeds`() {

        feedUseCase.stub {
            on { runBlocking { getLawyerFeed() } } doReturn Result.Success(
                listOf(
                    LawyerFeed(
                        id = ID,
                        noOfReviews = NUMBER_OF_REVIEWS,
                        rating = RATING,
                        firstName = FIRST_NAME,
                        description = DESCRIPTION
                    )
                )
            )
        }

        feedViewModel.dataLoadingStopEvent.observeForever(observerSingleEvent)
        mainCoroutineRule.pauseDispatcher()

        feedViewModel.loadLawyerFeed()

        verify(observerSingleEvent, never()).onChanged(null)

        mainCoroutineRule.resumeDispatcher()

        assertFalse(feedViewModel.errorMessageDisplayStatusEvent.getData())
        assertNull(feedViewModel.errorScreenEvent.getData())
        assertNotNull(feedViewModel.lawyerFeedEvent.getData())

        verify(observerSingleEvent).onChanged(null)
    }

    @Test
    fun `display error screen when viewmodel receive a no network error`() {

        feedUseCase.stub {
            on { runBlocking { getLawyerFeed() } } doReturn Result.Error(Result.NO_NETWORK)
        }

        feedViewModel.dataLoadingStopEvent.observeForever(observerSingleEvent)
        mainCoroutineRule.pauseDispatcher()

        feedViewModel.loadLawyerFeed()

        verify(observerSingleEvent, never()).onChanged(null)

        mainCoroutineRule.resumeDispatcher()

        assertTrue(feedViewModel.errorMessageDisplayStatusEvent.getData())
        assertEquals(
            feedViewModel.errorScreenEvent.getData().absoluteValue,
            R.string.feed_network_error_message
        )

        verify(observerSingleEvent).onChanged(null)
    }

    @Test
    fun `display error screen when viewmodel receive an any other generic error`() {

        feedUseCase.stub {
            on { runBlocking { getLawyerFeed() } } doReturn Result.Error("Other Errors")
        }

        feedViewModel.dataLoadingStopEvent.observeForever(observerSingleEvent)
        mainCoroutineRule.pauseDispatcher()

        feedViewModel.loadLawyerFeed()

        verify(observerSingleEvent, never()).onChanged(null)

        mainCoroutineRule.resumeDispatcher()

        assertTrue(feedViewModel.errorMessageDisplayStatusEvent.getData())
        assertEquals(
            feedViewModel.errorScreenEvent.getData().absoluteValue,
            R.string.feed_other_error
        )

        verify(observerSingleEvent).onChanged(null)
    }

    private companion object {
        private const val RATING = 5.0
        private const val NUMBER_OF_REVIEWS = 10
        private const val ID = 123
        private const val FIRST_NAME = "First Name"
        private const val DESCRIPTION = "Description"
    }
}
package com.zavaitar.feature.feed

import com.nhaarman.mockitokotlin2.whenever
import com.zavaitar.core.network.api.LawyerFeedApi
import com.zavaitar.core.network.model.LawyerFeedResponse
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.exceptions.misusing.WrongTypeOfReturnValue
import org.mockito.junit.MockitoJUnitRunner
import javax.inject.Provider
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

@RunWith(MockitoJUnitRunner::class)
class FeedManagerTest {

    @Mock lateinit var lawyerFeedApiProvider: Provider<LawyerFeedApi>

    @Mock lateinit var lawyerFeedResponse: LawyerFeedResponse

    private lateinit var feedManager: FeedManagerImpl

    @Before
    fun setup() {
        feedManager = FeedManagerImpl(lawyerFeedApiProvider)
    }

    @Test
    fun `convert lawyer feed response to list of lawyer feed transformation objects`() {
        whenever(lawyerFeedResponse.id).thenReturn(ID)
        whenever(lawyerFeedResponse.description).thenReturn(DESCRIPTION)
        val originalList = listOf(lawyerFeedResponse)

        val result = feedManager.convertToLawyerFeed(originalList)

        assertEquals(lawyerFeedResponse.id, result[0].id)
        assertEquals(lawyerFeedResponse.description, result[0].description)
    }

    @Test(expected = WrongTypeOfReturnValue::class)
    fun `convert lawyer feed and check with a null field return exception`() {
        whenever(lawyerFeedResponse.id).thenReturn(null)
        whenever(lawyerFeedResponse.description).thenReturn(DESCRIPTION)
        val originalList = listOf(lawyerFeedResponse)

        val result = feedManager.convertToLawyerFeed(originalList)

        assertNotEquals(lawyerFeedResponse.id, result[0].id)
        assertEquals(lawyerFeedResponse.description, result[0].description)
    }

    private companion object {
        private const val ID = 123
        private const val DESCRIPTION = "Description Goes Here"
    }
}
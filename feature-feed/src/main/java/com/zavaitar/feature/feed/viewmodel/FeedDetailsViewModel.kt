package com.zavaitar.feature.feed.viewmodel

import androidx.lifecycle.ViewModel
import com.zavaitar.core.viewmodel.SingleLiveEvent
import com.zavaitar.feature.feed.model.LawyerFeed
import javax.inject.Inject

internal class FeedDetailsViewModel @Inject constructor(): ViewModel() {

    val detailsDisplayEvent = SingleLiveEvent<LawyerFeed>()

    fun initSelectedLawyerData(data: Map<String, Any?>) {
        detailsDisplayEvent.value = data[SELECTED_DETAIL_KEY] as LawyerFeed?
    }

    private companion object {
        private const val SELECTED_DETAIL_KEY = "SELECTED_DETAIL_KEY"
    }
}
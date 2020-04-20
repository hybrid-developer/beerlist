package com.zavaitar.interviewproject.navigation

import android.os.Bundle
import android.view.View
import androidx.navigation.Navigation
import com.zavaitar.feature.feed.model.LawyerFeed
import com.zavaitar.feature.feed.navigation.FeedNavigator
import com.zavaitar.interviewproject.R
import javax.inject.Inject

internal class FeedNavigationController @Inject constructor() : BaseNavigationController(),
    FeedNavigator {

    override fun navigateToDetailsScreen(view: View, lawyerFeed: LawyerFeed?) {
        Navigation.findNavController(view).navigate(R.id.feedDetailsFragment,
            Bundle().apply {
                putParcelable(SELECTED_DETAIL_KEY, lawyerFeed)
            }
        )
    }

    private companion object {
        private const val SELECTED_DETAIL_KEY = "SELECTED_DETAIL_KEY"
    }
}
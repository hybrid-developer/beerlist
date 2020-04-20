package com.zavaitar.feature.feed.navigation

import android.view.View
import com.zavaitar.feature.feed.model.LawyerFeed

interface FeedNavigator {

    fun navigateToDetailsScreen(view: View, lawyerFeed: LawyerFeed?)
}
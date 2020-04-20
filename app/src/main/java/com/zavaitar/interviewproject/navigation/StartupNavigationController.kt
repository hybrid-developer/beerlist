package com.zavaitar.interviewproject.navigation

import android.view.View
import androidx.navigation.Navigation.findNavController
import com.zavaitar.feature.startup.navigation.StartupNavigator
import com.zavaitar.interviewproject.R
import javax.inject.Inject

internal class StartupNavigationController @Inject constructor() :
    BaseNavigationController(), StartupNavigator {

    override fun navigateToHomeScreen(view: View) = showHomeScreen(view)

    override fun navigateToTwitterFeeds(view: View) =
        findNavController(view).navigate(R.id.FeedActivity)

}
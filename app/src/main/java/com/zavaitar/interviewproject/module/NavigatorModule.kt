package com.zavaitar.interviewproject.module

import com.zavaitar.feature.feed.navigation.FeedNavigator
import com.zavaitar.feature.startup.navigation.StartupNavigator
import com.zavaitar.interviewproject.navigation.FeedNavigationController
import com.zavaitar.interviewproject.navigation.StartupNavigationController
import dagger.Binds
import dagger.Module

@Module
abstract class NavigatorModule {

    @Binds
    internal abstract fun bindStartupNavigator(impl: StartupNavigationController): StartupNavigator

    @Binds
    internal abstract fun bindFeedNavigator(impl: FeedNavigationController): FeedNavigator
}
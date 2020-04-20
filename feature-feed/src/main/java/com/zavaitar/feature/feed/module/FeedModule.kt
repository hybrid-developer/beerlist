package com.zavaitar.feature.feed.module

import com.zavaitar.feature.feed.FeedManager
import com.zavaitar.feature.feed.FeedManagerImpl
import com.zavaitar.feature.feed.FeedUseCase
import com.zavaitar.feature.feed.FeedUseCaseImpl
import com.zavaitar.feature.feed.presentation.FeedActivity
import com.zavaitar.feature.feed.presentation.FeedDetailsFragment
import com.zavaitar.feature.feed.presentation.FeedFragment
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import javax.inject.Singleton

@Module
abstract class FeedModule {

    @ContributesAndroidInjector(modules = [FeedFragmentModule::class])
    internal abstract fun contributeFeedFragment(): FeedFragment

    @ContributesAndroidInjector(modules = [FeedDetailsFragmentModule::class])
    internal abstract fun contributeFeedDetailsFragment(): FeedDetailsFragment

    @ContributesAndroidInjector
    internal abstract fun contributeFeedActivity(): FeedActivity

    @Binds
    internal abstract fun bindFeedUseCase(impl: FeedUseCaseImpl): FeedUseCase

    @Binds
    @Singleton
    internal abstract fun bindFeedManager(impl: FeedManagerImpl): FeedManager
}
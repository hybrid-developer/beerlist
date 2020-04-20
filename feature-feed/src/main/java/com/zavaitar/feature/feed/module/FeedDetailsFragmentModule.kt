package com.zavaitar.feature.feed.module

import androidx.lifecycle.ViewModel
import com.zavaitar.core.viewmodel.ViewModelKey
import com.zavaitar.feature.feed.viewmodel.FeedDetailsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal abstract class FeedDetailsFragmentModule {

    @Binds
    @IntoMap
    @ViewModelKey(FeedDetailsViewModel::class)
    internal abstract fun bindFeedViewModel(viewModel: FeedDetailsViewModel): ViewModel
}
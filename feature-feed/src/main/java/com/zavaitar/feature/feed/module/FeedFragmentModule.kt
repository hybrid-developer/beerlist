package com.zavaitar.feature.feed.module

import androidx.lifecycle.ViewModel
import com.zavaitar.core.viewmodel.ViewModelKey
import com.zavaitar.feature.feed.viewmodel.FeedViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal abstract class FeedFragmentModule {

    @Binds
    @IntoMap
    @ViewModelKey(FeedViewModel::class)
    internal abstract fun bindFeedViewModel(viewModel: FeedViewModel): ViewModel
}
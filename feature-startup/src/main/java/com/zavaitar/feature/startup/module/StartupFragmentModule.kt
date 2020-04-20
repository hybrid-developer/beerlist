package com.zavaitar.feature.startup.module

import androidx.lifecycle.ViewModel
import com.zavaitar.core.viewmodel.ViewModelKey
import com.zavaitar.feature.startup.viewmodel.StartupViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class StartupFragmentModule {

    @Binds
    @IntoMap
    @ViewModelKey(StartupViewModel::class)
    internal abstract fun bindStartupViewModel(viewModel: StartupViewModel): ViewModel
}
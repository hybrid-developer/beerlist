package com.zavaitar.feature.startup.module

import com.zavaitar.feature.startup.presentation.StartupActivity
import com.zavaitar.feature.startup.presentation.StartupFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class StartupModule {

    @ContributesAndroidInjector(modules = [StartupFragmentModule::class])
    internal abstract fun contributeStartupFragment(): StartupFragment

    @ContributesAndroidInjector
    internal abstract fun contributeStartupActivity(): StartupActivity
}
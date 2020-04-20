package com.zavaitar.interviewproject.module

import com.zavaitar.interviewproject.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityModule {

    @ContributesAndroidInjector
    internal  abstract fun contributesMainActivity(): MainActivity
}
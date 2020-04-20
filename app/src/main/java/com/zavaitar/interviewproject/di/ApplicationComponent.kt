package com.zavaitar.interviewproject.di

import android.content.Context
import com.zavaitar.core.network.NetworkModule
import com.zavaitar.core.network.module.TwitterFeedApiModule
import com.zavaitar.feature.feed.module.FeedModule
import com.zavaitar.feature.startup.module.StartupModule
import com.zavaitar.interviewproject.MainApplication
import com.zavaitar.interviewproject.module.MainActivityModule
import com.zavaitar.interviewproject.module.NavigatorModule
import com.zavaitar.interviewproject.util.AppModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        NavigatorModule::class,
        NetworkModule::class,
        MainActivityModule::class,
        StartupModule::class,
        FeedModule::class,
        TwitterFeedApiModule::class
    ]
)

interface ApplicationComponent : AndroidInjector<MainApplication> {
    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(context: Context): Builder

        fun networkModule(networkModule: NetworkModule): Builder

        fun twitterFeedApiModule(twitterFeedApiModule: TwitterFeedApiModule): Builder

        fun build(): ApplicationComponent
    }
}
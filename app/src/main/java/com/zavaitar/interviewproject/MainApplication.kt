package com.zavaitar.interviewproject

import androidx.lifecycle.LifecycleCallbacks
import com.zavaitar.interviewproject.di.ApplicationComponent
import dagger.android.DaggerApplication
import uk.co.chrisjenx.calligraphy.CalligraphyConfig

class MainApplication : DaggerApplication() {

    override fun onCreate() = super.onCreate().also {
        registerActivityLifecycleCallbacks(LifecycleCallbacks.activityListener())
        CalligraphyConfig.initDefault(CalligraphyConfig.Builder()
            .setDefaultFontPath("fonts/sans_light.ttf")
            .setFontAttrId(R.attr.fontPath)
            .build())
    }

    override fun applicationInjector(): ApplicationComponent = Config.applicationInjector(this)
}
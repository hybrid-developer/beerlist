package com.zavaitar.interviewproject.util

import com.zavaitar.interviewproject.BuildConfig
import javax.inject.Inject

interface AppConfigurations {
    fun appVersion(): String
}

class AppConfigurationsImpl @Inject constructor() : AppConfigurations {
    override fun appVersion() = BuildConfig.VERSION_NAME
}

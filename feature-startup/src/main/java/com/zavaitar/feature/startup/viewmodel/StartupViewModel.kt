package com.zavaitar.feature.startup.viewmodel

import androidx.lifecycle.ViewModel
import com.zavaitar.core.viewmodel.SingleLiveEvent
import javax.inject.Inject

internal class StartupViewModel @Inject constructor() : ViewModel() {

    val splashDidLoadEvent = SingleLiveEvent<Void>()

    fun init() {
        splashDidLoadEvent.call()
    }
}

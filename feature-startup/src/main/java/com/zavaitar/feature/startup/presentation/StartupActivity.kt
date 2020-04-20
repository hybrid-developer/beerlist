package com.zavaitar.feature.startup.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.zavaitar.feature.startup.R
import dagger.android.AndroidInjection

internal class StartupActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.startup_activity)
        AndroidInjection.inject(this)
    }
}
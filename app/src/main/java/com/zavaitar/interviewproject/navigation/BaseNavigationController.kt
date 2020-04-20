package com.zavaitar.interviewproject.navigation

import android.view.View
import androidx.navigation.Navigation.findNavController
import com.zavaitar.interviewproject.R
import javax.inject.Inject

internal open class BaseNavigationController @Inject constructor() {

    fun showHomeScreen(view: View) {
        findNavController(view).navigate(R.id.homeFragment)
    }
}
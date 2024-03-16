package com.tminus1010.tminustasker.environment.android_wrapper

import android.annotation.SuppressLint
import androidx.navigation.NavController
import com.tminus1010.tmcommonkotlin.androidx.launchOnMainThread
import com.tminus1010.tminustasker.R
import dagger.Reusable
import javax.inject.Inject

@Reusable
class AndroidNavigationWrapperImpl @Inject constructor() : AndroidNavigationWrapper {

    override fun navToDashboard() = launchOnMainThread {
        nav.navigate(R.id.navigation_dashboard)
    }

    override fun navTo(id: Int) = launchOnMainThread {
        nav.navigate(id)
    }

    private val nav get() = Companion.nav ?: error("This class expects Companion.nav to be assigned")

    companion object {
        // This pattern can cause memory leaks. However, this nav controller lasts for the entire application, so it should be fine.
        @SuppressLint("StaticFieldLeak")
        var nav: NavController? = null
    }
}
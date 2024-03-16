package com.tminus1010.tminustasker.ui

import com.tminus1010.tminustasker.environment.android_wrapper.AndroidNavigationWrapper
import dagger.Reusable
import javax.inject.Inject

@Reusable
class Navigator @Inject constructor(
    private val androidNavigationWrapper: AndroidNavigationWrapper,
) : AndroidNavigationWrapper by androidNavigationWrapper
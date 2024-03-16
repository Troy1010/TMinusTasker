package com.tminus1010.tminustasker.environment

import android.app.Application
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.tminus1010.tminustasker.environment.android_wrapper.AndroidNavigationWrapper
import com.tminus1010.tminustasker.environment.android_wrapper.AndroidNavigationWrapperImpl
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
open class EnvironmentModule {
    @Provides
    @Reusable
    open fun provideDataStore(application: Application): DataStore<Preferences> =
        application.dataStore

    @Provides
    fun provideAndroidNavigationWrapper(): AndroidNavigationWrapper = AndroidNavigationWrapperImpl()
}
package com.tminus1010.tminustasker.data

import android.app.Application
import com.tminus1010.tminustasker.environment.CurrentDateProvider

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CurrentDateRepo @Inject constructor(app: Application) : CurrentDateProvider(app)
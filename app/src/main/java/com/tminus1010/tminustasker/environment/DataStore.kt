package com.tminus1010.tminustasker.environment

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore


val Context.dataStore by preferencesDataStore("dataStore")
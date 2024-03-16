package com.tminus1010.tminustasker.data.easy_data_store

import kotlinx.coroutines.flow.SharedFlow

interface EasyDataStore<T> {
    val flow: SharedFlow<T>
    fun setDefault()
    fun set(x: T)
}
package com.tminus1010.tminustasker.all_layers.extensions

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.shareIn

/**
 * This is a convenience method.
 *
 * It is very similar to .stateIn(), except it doesn't mess with your ability to use .first(). It will only give the default value if it was null otherwise.
 */
fun <T> Flow<T?>.easyShareIn(scope: CoroutineScope, started: SharingStarted, defaultValue: T): SharedFlow<T> {
    return map { it ?: defaultValue }.distinctUntilChanged().shareIn(scope, started, 1)
}

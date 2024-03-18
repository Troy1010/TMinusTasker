package com.tminus1010.tminustasker.environment

import android.app.Application
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

open class CurrentDateProvider @Inject constructor(
    private val app: Application
) {
    val flow =
        channelFlow<LocalDate> {
            val broadcastReceiver =
                object : BroadcastReceiver() {
                    override fun onReceive(context: Context, intent: Intent) {
                        GlobalScope.launch {
                            send(LocalDate.now())
                        }
                    }
                }
            app.registerReceiver(
                broadcastReceiver,
                IntentFilter().apply {
                    addAction(Intent.ACTION_TIME_TICK)
                    addAction(Intent.ACTION_TIMEZONE_CHANGED)
                    addAction(Intent.ACTION_TIME_CHANGED)
                }
            )
            awaitClose { app.unregisterReceiver(broadcastReceiver) }
        }
            .stateIn(GlobalScope, SharingStarted.WhileSubscribed(1000), LocalDate.now())
}
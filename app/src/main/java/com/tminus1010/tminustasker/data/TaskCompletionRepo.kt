package com.tminus1010.tminustasker.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.squareup.moshi.JsonAdapter
import com.tminus1010.tmcommonkotlin.core.tryOrNull
import com.tminus1010.tminustasker.all_layers.extensions.easyShareIn
import com.tminus1010.tminustasker.domain.TaskCompletion
import com.tminus1010.tminustasker.environment.adapter.MoshiProvider
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

// TODO: This could be heavily cleaned up. There should be an easier way to store a List<TaskCompletion>.
// TODO: Should use MongoDB or SQL for performance.
class TaskCompletionRepo @Inject constructor(
    private val dataStore: DataStore<Preferences>,
) {
    fun adapter(): JsonAdapter<List<TaskCompletion>> {
        val type = com.squareup.moshi.Types.newParameterizedType(List::class.java, TaskCompletion::class.java)
        return MoshiProvider.moshi.adapter<List<TaskCompletion>>(type)
    }

    val defaultValue = listOf<TaskCompletion>()
    private val key = stringPreferencesKey("TaskCompletion")

    val flow =
        dataStore.data
            .map { tryOrNull { adapter().fromJson(it[key] ?: "null") } }
            .easyShareIn(GlobalScope, SharingStarted.Eagerly, defaultValue)

    fun setDefault() = set(defaultValue)
    fun set(x: List<TaskCompletion>) {
        GlobalScope.launch { dataStore.edit { it[key] = adapter().toJson(x) } }
    }


    suspend fun add(taskCompletion: TaskCompletion) {
        set(flow.first().plus(taskCompletion)) // TODO: Still need to make this robust against race conditions.
    }

    suspend fun remove(taskCompletion: TaskCompletion) {
        set(flow.first().minus(taskCompletion)) // TODO: Still need to make this robust against race conditions.
    }
}
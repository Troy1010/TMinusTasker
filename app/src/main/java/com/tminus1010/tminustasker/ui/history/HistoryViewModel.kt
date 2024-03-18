package com.tminus1010.tminustasker.ui.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tminus1010.tmcommonkotlin.androidx.ShowToast
import com.tminus1010.tminustasker.data.CategoryRepo
import com.tminus1010.tminustasker.data.TaskCompletionRepo
import com.tminus1010.tminustasker.domain.MainInteractor
import com.tminus1010.tminustasker.environment.android_wrapper.ActivityWrapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val mainInteractor: MainInteractor,
    private val activityWrapper: ActivityWrapper,
    private val showToast: ShowToast,
    private val taskCompletionRepo: TaskCompletionRepo,
    private val categoryRepo: CategoryRepo,
) : ViewModel() {
    // # User Intents
    suspend fun userDoesSomething() {
    }

    // # State
    val state =
        mainInteractor.taskCompletions
            .map { taskCompletions ->
                taskCompletions
                    .groupBy { it.localDateTime.toLocalDate() }
            }
            .stateIn(viewModelScope, SharingStarted.Eagerly, mapOf())
}
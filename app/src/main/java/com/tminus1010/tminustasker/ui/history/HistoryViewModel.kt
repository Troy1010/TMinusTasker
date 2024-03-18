package com.tminus1010.tminustasker.ui.history

import androidx.lifecycle.ViewModel
import com.tminus1010.tmcommonkotlin.androidx.ShowToast
import com.tminus1010.tminustasker.data.CategoryRepo
import com.tminus1010.tminustasker.data.TaskCompletionRepo
import com.tminus1010.tminustasker.domain.MainInteractor
import com.tminus1010.tminustasker.environment.android_wrapper.ActivityWrapper
import dagger.hilt.android.lifecycle.HiltViewModel
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
}
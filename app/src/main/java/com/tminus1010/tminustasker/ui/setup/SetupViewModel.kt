package com.tminus1010.tminustasker.ui.setup

import androidx.lifecycle.ViewModel
import com.tminus1010.tmcommonkotlin.androidx.ShowToast
import com.tminus1010.tminustasker.data.CategoryRepo
import com.tminus1010.tminustasker.data.TaskCompletionRepo
import com.tminus1010.tminustasker.domain.MainInteractor
import com.tminus1010.tminustasker.environment.android_wrapper.ActivityWrapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class SetupViewModel @Inject constructor(
    private val mainInteractor: MainInteractor,
    private val activityWrapper: ActivityWrapper,
    private val showToast: ShowToast,
    private val taskCompletionRepo: TaskCompletionRepo,
    private val categoryRepo: CategoryRepo,
) : ViewModel() {
    // # User Intents
    suspend fun userAddCategory() {
        mainInteractor.createCategory(userInput.value)
        showToast("Category Added: ${userInput.value}")
        userInput.emit("")
    }

    suspend fun userPlayground() {
        activityWrapper.showAlertDialog.invoke("Resetting task completions")
        taskCompletionRepo.setDefault()
    }

    suspend fun userClearTaskCompletions() {
        activityWrapper.showAlertDialog.invoke("Clearing task completions")
        taskCompletionRepo.setDefault()
    }

    suspend fun userClearCategories() {
        activityWrapper.showAlertDialog.invoke("Clearing categories")
        categoryRepo.setDefault()
    }

    // # State
    val userInput = MutableStateFlow("")
}
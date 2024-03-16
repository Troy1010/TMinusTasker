package com.tminus1010.tminustasker.ui.add_category

import androidx.lifecycle.ViewModel
import com.tminus1010.tmcommonkotlin.androidx.ShowToast
import com.tminus1010.tmcommonkotlin.view.NativeText
import com.tminus1010.tminustasker.R
import com.tminus1010.tminustasker.data.TaskCompletionRepo
import com.tminus1010.tminustasker.domain.MainInteractor
import com.tminus1010.tminustasker.environment.android_wrapper.ActivityWrapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class AddCategoryViewModel @Inject constructor(
    private val mainInteractor: MainInteractor,
    private val activityWrapper: ActivityWrapper,
    private val showToast: ShowToast,
    private val taskCompletionRepo: TaskCompletionRepo,
) : ViewModel() {
    // # User Intents
    suspend fun userSubmit() {
        mainInteractor.createCategory(userInputCategoryName.value)
        showToast(NativeText.Resource(R.string.submitted_successfully))
    }

    suspend fun userPlayground() {
        activityWrapper.showAlertDialog.invoke("Resetting task completions")
        taskCompletionRepo.setDefault()
    }

    // # State
    val userInputCategoryName = MutableStateFlow("")
}
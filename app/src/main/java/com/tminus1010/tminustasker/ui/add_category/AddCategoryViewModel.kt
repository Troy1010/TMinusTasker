package com.tminus1010.tminustasker.ui.add_category

import androidx.lifecycle.ViewModel
import com.tminus1010.tminustasker.domain.CategoryInteractor
import com.tminus1010.tminustasker.environment.android_wrapper.ActivityWrapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class AddCategoryViewModel @Inject constructor(
    private val categoryInteractor: CategoryInteractor,
    private val activityWrapper: ActivityWrapper,
) : ViewModel() {
    // # User Intents
    suspend fun userSubmit() {
        categoryInteractor.addCategory(userInputCategoryName.value)
        toastSuccessfullySubmitted.emit(Unit)
    }

    suspend fun userPlayground() {
        activityWrapper.showAlertDialog.invoke("Playground")
    }

    // # State
    val userInputCategoryName = MutableStateFlow("")
    val toastSuccessfullySubmitted = MutableSharedFlow<Unit>()
}
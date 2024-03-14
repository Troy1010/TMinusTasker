package com.tminus1010.tminustasker.ui.add_category

import androidx.lifecycle.ViewModel
import com.tminus1010.tminustasker.domain.CategoryInteractor
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow

class AddCategoryViewModel : ViewModel() {
    suspend fun userSubmit() {
        CategoryInteractor.addCategory(userInputCategoryName.value)
        toastSuccessfullySubmitted.emit(Unit)
    }

    val userInputCategoryName = MutableStateFlow("")
    val toastSuccessfullySubmitted = MutableSharedFlow<Unit>()
}
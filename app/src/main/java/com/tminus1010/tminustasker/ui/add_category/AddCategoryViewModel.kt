package com.tminus1010.tminustasker.ui.add_category

import androidx.lifecycle.ViewModel
import com.tminus1010.tminustasker.domain.CategoryInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class AddCategoryViewModel @Inject constructor(
    private val categoryInteractor: CategoryInteractor,
) : ViewModel() {
    suspend fun userSubmit() {
        categoryInteractor.addCategory(userInputCategoryName.value)
        toastSuccessfullySubmitted.emit(Unit)
    }

    val userInputCategoryName = MutableStateFlow("")
    val toastSuccessfullySubmitted = MutableSharedFlow<Unit>()
}
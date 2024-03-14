package com.tminus1010.tminustasker.ui.home

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class AddCategoryViewModel : ViewModel() {
    val userInputCategoryName = MutableStateFlow("")
}
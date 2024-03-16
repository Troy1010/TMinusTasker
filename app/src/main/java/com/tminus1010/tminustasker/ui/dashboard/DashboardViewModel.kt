package com.tminus1010.tminustasker.ui.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tminus1010.tminustasker.domain.CategoryInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val categoryInteractor: CategoryInteractor,
) : ViewModel() {
    val categories =
        categoryInteractor.categories
            .stateIn(viewModelScope, SharingStarted.Eagerly, listOf())
}
package com.tminus1010.tminustasker.ui.dashboard

import androidx.lifecycle.ViewModel
import com.tminus1010.tminustasker.domain.CategoryInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val categoryInteractor: CategoryInteractor,
) : ViewModel() {
    val categories = categoryInteractor.categories
}
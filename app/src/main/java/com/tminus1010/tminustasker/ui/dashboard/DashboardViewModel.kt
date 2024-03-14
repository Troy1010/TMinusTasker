package com.tminus1010.tminustasker.ui.dashboard

import androidx.lifecycle.ViewModel
import com.tminus1010.tminustasker.domain.CategoryInteractor

class DashboardViewModel : ViewModel() {

    val categories = CategoryInteractor.categories
}
package com.tminus1010.tminustasker.ui.dashboard

data class CategoryViewModelItem(
    val categoryName: String,
    val backgroundColor: Int,
    val textColor: Int,
    val onClick: () -> Unit,
)
package com.tminus1010.tminustasker.ui.dashboard

data class CategoryViewModelItem(
    val categoryName: String,
    val color: Int,
    val onClick: () -> Unit,
)
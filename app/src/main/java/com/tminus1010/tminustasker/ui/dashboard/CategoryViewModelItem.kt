package com.tminus1010.tminustasker.ui.dashboard

import com.tminus1010.tminustasker.ui.all_features.vm_item.MenuVMItem


data class CategoryViewModelItem(
    val categoryName: String,
    val backgroundColor: Int,
    val textColor: Int,
    val menuVMItems: List<MenuVMItem>,
    val onClick: () -> Unit,
)
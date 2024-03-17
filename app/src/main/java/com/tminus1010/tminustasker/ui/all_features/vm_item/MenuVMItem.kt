package com.tminus1010.tminustasker.ui.all_features.vm_item

import com.tminus1010.tminustasker.ui.all_features.ComposableNativeText

data class MenuVMItem(
    val text: ComposableNativeText,
    val onClick: () -> Unit,
)
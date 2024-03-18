package com.tminus1010.tminustasker.all_layers.extensions

import androidx.annotation.AttrRes
import androidx.compose.ui.graphics.Color
import androidx.fragment.app.Fragment
import com.tminus1010.tmcommonkotlin.androidx.extensions.getColorByAttr

fun Fragment.colorByAttr(@AttrRes id: Int) =
    Color(requireContext().theme.getColorByAttr(id))
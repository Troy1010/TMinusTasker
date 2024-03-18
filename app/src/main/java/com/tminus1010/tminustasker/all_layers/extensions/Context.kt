package com.tminus1010.tminustasker.all_layers.extensions

import android.content.Context
import androidx.annotation.AttrRes
import androidx.compose.ui.graphics.Color
import com.tminus1010.tmcommonkotlin.androidx.extensions.getColorByAttr

fun Context.getColorByAttr(@AttrRes id: Int): Color =
    Color(theme.getColorByAttr(id))
package com.tminus1010.tminustasker.ui.all_features

import android.content.Context
import androidx.annotation.PluralsRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import com.tminus1010.tmcommonkotlin.view.NativeText

sealed class ComposableNativeText {
    abstract fun toCharSequence(context: Context): CharSequence

    @Composable
    @ReadOnlyComposable
    abstract fun stringResource(): String

    abstract fun toNativeText(): NativeText

    data class Simple(val text: String) : ComposableNativeText() {
        override fun toCharSequence(context: Context): CharSequence {
            return text
        }

        @Composable
        @ReadOnlyComposable
        override fun stringResource(): String {
            return text
        }

        override fun toNativeText(): NativeText {
            return NativeText.Simple(text)
        }
    }

    data class Resource(@StringRes val id: Int) : ComposableNativeText() {
        override fun toCharSequence(context: Context): CharSequence {
            return context.getString(id)
        }

        @Composable
        @ReadOnlyComposable
        override fun stringResource(): String {
            return stringResource(id = id)
        }

        override fun toNativeText(): NativeText {
            return NativeText.Resource(id)
        }
    }

    data class Plural(@PluralsRes val id: Int, val number: Int, val args: List<Any>) : ComposableNativeText() {
        constructor(@PluralsRes id: Int, number: Int, vararg args: Any) : this(id, number, args.toList())

        override fun toCharSequence(context: Context): CharSequence {
            return context.resources.getQuantityString(id, number, *args.toTypedArray())
        }

        @Composable
        @ReadOnlyComposable
        override fun stringResource(): String {
            return pluralStringResource(id = id, count = number, *args.toTypedArray())
        }

        override fun toNativeText(): NativeText {
            return NativeText.Plural(id, number, *args.toTypedArray())
        }
    }

    data class Arguments(@StringRes val id: Int, val args: List<Any>) : ComposableNativeText() {
        constructor(@StringRes id: Int, vararg args: Any) : this(id, args.toList())

        override fun toCharSequence(context: Context): CharSequence {
            return context.getString(id, *args.toTypedArray())
        }

        @Composable
        @ReadOnlyComposable
        override fun stringResource(): String {
            return stringResource(id = id, *args.toTypedArray())
        }

        override fun toNativeText(): NativeText {
            return NativeText.Arguments(id, *args.toTypedArray())
        }
    }

    data class Multi(val composableNativeTexts: List<ComposableNativeText>) : ComposableNativeText() {
        constructor(vararg composableNativeTexts: ComposableNativeText) : this(composableNativeTexts.toList())

        override fun toCharSequence(context: Context): CharSequence {
            return StringBuilder()
                .apply { composableNativeTexts.forEach { append(it.toCharSequence(context)) } }
                .toString()
        }

        @Composable
        @ReadOnlyComposable
        override fun stringResource(): String {
            return StringBuilder()
                .apply { composableNativeTexts.forEach { append(it.stringResource()) } }
                .toString()
        }

        override fun toNativeText(): NativeText {
            return NativeText.Multi(composableNativeTexts.map { it.toNativeText() })
        }
    }
}
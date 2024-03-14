package com.tminus1010.tminustasker.domain

import kotlinx.coroutines.flow.MutableStateFlow

// TODO: Make this a class, use Dagger to inject it.
object CategoryInteractor {

    val categories = MutableStateFlow(listOf<String>())

    suspend fun addCategory(categoryName: String) {
        categories.emit(categories.value.plus(categoryName))
    }
}
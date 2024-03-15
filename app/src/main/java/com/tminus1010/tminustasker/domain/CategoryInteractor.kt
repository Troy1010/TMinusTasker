package com.tminus1010.tminustasker.domain

import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class CategoryInteractor @Inject constructor() {

    val categories = MutableStateFlow(listOf<String>())

    suspend fun addCategory(categoryName: String) {
        categories.emit(categories.value.plus(categoryName))
    }
}
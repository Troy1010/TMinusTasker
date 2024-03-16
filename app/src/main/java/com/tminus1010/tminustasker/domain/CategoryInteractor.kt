package com.tminus1010.tminustasker.domain

import com.tminus1010.tminustasker.data.CategoryRepo
import kotlinx.coroutines.flow.SharedFlow
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class CategoryInteractor @Inject constructor(
    private val categoryRepo: CategoryRepo,
) {
    val categories: SharedFlow<List<String>> = categoryRepo.flow

    suspend fun addCategory(categoryName: String) {
        categoryRepo.add(categoryName)
    }
}
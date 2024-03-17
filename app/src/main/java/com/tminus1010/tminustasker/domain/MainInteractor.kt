package com.tminus1010.tminustasker.domain

import com.tminus1010.tmcommonkotlin.tuple.tuple
import com.tminus1010.tminustasker.all_layers.extensions.easyShareIn
import com.tminus1010.tminustasker.data.CategoryRepo
import com.tminus1010.tminustasker.data.TaskCompletionRepo
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import java.time.LocalDate
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class MainInteractor @Inject constructor(
    private val categoryRepo: CategoryRepo,
    private val taskCompletionRepo: TaskCompletionRepo,
) {
    val categories =
        combine(categoryRepo.flow, taskCompletionRepo.flow, ::tuple)
            .map { (categories, taskCompletions) ->
                categories.map { category ->
                    CategoryInfo(
                        category,
                        taskCompletions
                            .filter { it.categoryName == category }
                            .maxByOrNull { it.localDate }
                            ?.localDate
                    )
                }
            }
            .easyShareIn(GlobalScope, SharingStarted.Eagerly, listOf())

    suspend fun createCategory(categoryName: String) {
        categoryRepo.add(categoryName)
    }

    suspend fun removeCategory(categoryName: String) {
        categoryRepo.remove(categoryName)
    }

    suspend fun createTaskCompletion(categoryName: String, message: String?) {
        taskCompletionRepo.add(TaskCompletion(categoryName, message, LocalDate.now()))
    }
}
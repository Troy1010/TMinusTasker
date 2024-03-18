package com.tminus1010.tminustasker.domain

import com.tminus1010.tmcommonkotlin.tuple.tuple
import com.tminus1010.tminustasker.all_layers.extensions.easyShareIn
import com.tminus1010.tminustasker.data.CategoryRepo
import com.tminus1010.tminustasker.data.CurrentDateRepo
import com.tminus1010.tminustasker.data.TaskCompletionRepo
import com.tminus1010.tminustasker.domain.model.CategoryInfo
import com.tminus1010.tminustasker.domain.model.TaskCompletion
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import java.time.LocalDateTime
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class MainInteractor @Inject constructor(
    private val categoryRepo: CategoryRepo,
    private val taskCompletionRepo: TaskCompletionRepo,
    private val currentDateRepo: CurrentDateRepo,
    private val localDateTimeToLocalDateWithAdjustmentConverter: LocalDateTimeToLocalDateWithAdjustmentConverter,
) {
    suspend fun createCategory(categoryName: String) {
        categoryRepo.add(categoryName)
    }

    suspend fun removeCategory(categoryName: String) {
        categoryRepo.remove(categoryName)
    }

    suspend fun createTaskCompletion(categoryName: String, message: String?) {
        taskCompletionRepo.add(TaskCompletion(categoryName, message, LocalDateTime.now()))
    }

    val categories =
        combine(categoryRepo.flow, taskCompletionRepo.flow, currentDateRepo.flow, ::tuple)
            .map { (categories, taskCompletions, currentDate) ->
                categories.map { category ->
                    CategoryInfo(
                        categoryName = category,
                        completions = taskCompletions
                            .filter { it.categoryName == category },
                        currentLocalDate = currentDate,
                        localDateTimeToLocalDateWithAdjustmentConverter = localDateTimeToLocalDateWithAdjustmentConverter
                    )
                }
            }
            .easyShareIn(GlobalScope, SharingStarted.Eagerly, listOf())

    val taskCompletions =
        taskCompletionRepo.flow
}
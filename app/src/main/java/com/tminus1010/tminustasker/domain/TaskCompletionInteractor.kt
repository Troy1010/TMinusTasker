package com.tminus1010.tminustasker.domain

import com.tminus1010.tminustasker.data.TaskCompletionRepo
import kotlinx.coroutines.flow.first
import java.time.LocalDate
import javax.inject.Inject

class TaskCompletionInteractor @Inject constructor(
    private val taskCompletionRepo: TaskCompletionRepo,
    private val localDateTimeToLocalDateWithAdjustmentConverter: LocalDateTimeToLocalDateWithAdjustmentConverter,
) {

    // TODO: Make robust against race conditions.
    suspend fun removeEntriesForDay(date: LocalDate) {
        taskCompletionRepo.set(
            taskCompletionRepo.flow.first()
                .filterNot { localDateTimeToLocalDateWithAdjustmentConverter.reverse(it.localDateTime) == date }
        )
    }
}
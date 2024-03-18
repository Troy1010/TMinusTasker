package com.tminus1010.tminustasker.domain.model

import com.tminus1010.tminustasker.domain.LocalDateTimeToLocalDateWithAdjustmentConverter
import java.time.LocalDate

data class CategoryInfo(
    val categoryName: String,
    val completions: List<TaskCompletion>,
    val currentLocalDate: LocalDate,
    val localDateTimeToLocalDateWithAdjustmentConverter: LocalDateTimeToLocalDateWithAdjustmentConverter,
) {
    val todaysCompletions =
        completions
            .filter { localDateTimeToLocalDateWithAdjustmentConverter.convert(it.localDateTime) == currentLocalDate }
    val isCompletedToday get() = todaysCompletions.isNotEmpty()
}
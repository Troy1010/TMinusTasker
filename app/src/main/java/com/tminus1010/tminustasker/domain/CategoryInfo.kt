package com.tminus1010.tminustasker.domain

import java.time.LocalDate

data class CategoryInfo(
    val categoryName: String,
    val completions: List<TaskCompletion>,
    val currentLocalDate: LocalDate,
) {
    val todaysCompletions =
        completions
            .filter { it.localDateTime.toLocalDate() == currentLocalDate }
    val isCompletedToday get() = todaysCompletions.isNotEmpty()
}
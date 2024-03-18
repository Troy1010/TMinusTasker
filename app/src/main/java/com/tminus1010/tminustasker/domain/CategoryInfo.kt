package com.tminus1010.tminustasker.domain

import java.time.LocalDate

/**
 * for [dateOfCompletion], null means not completed
 */
data class CategoryInfo(
    val categoryName: String,
    val completions: List<TaskCompletion>,
) {
    val dateOfCompletion =
        completions
            .maxByOrNull { it.localDate }
            ?.localDate

    val todaysCompletions =
        completions
            .filter { it.localDate == LocalDate.now() } // TODO: should be reactive
    val isCompletedToday get() = todaysCompletions.isNotEmpty()
}
package com.tminus1010.tminustasker.domain

import java.time.LocalDate

/**
 * for [dateOfCompletion], null means not completed
 */
data class CategoryInfo(
    val categoryName: String,
    val dateOfCompletion: LocalDate?
) {
    val isCompletedToday get() = LocalDate.now() == dateOfCompletion // TODO: should be reactive
}
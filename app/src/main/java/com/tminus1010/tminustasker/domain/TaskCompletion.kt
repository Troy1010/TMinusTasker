package com.tminus1010.tminustasker.domain

import java.time.LocalDate

data class TaskCompletion(
    val categoryName: String,
    val message: String?,
    val localDate: LocalDate,
)
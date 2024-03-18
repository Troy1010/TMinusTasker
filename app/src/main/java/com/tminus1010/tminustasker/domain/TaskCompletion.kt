package com.tminus1010.tminustasker.domain

import java.time.LocalDateTime

data class TaskCompletion(
    val categoryName: String,
    val message: String?,
    val localDateTime: LocalDateTime,
)
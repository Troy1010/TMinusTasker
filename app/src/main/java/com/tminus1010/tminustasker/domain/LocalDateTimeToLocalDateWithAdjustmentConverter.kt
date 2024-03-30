package com.tminus1010.tminustasker.domain

import java.time.LocalDate
import java.time.LocalDateTime
import javax.inject.Inject

class LocalDateTimeToLocalDateWithAdjustmentConverter @Inject constructor() {
    val hoursAfterMidnightCounted = 5L

    fun convert(localDateTime: LocalDateTime): LocalDate {
        return localDateTime.minusHours(hoursAfterMidnightCounted).toLocalDate()
    }

    fun reverse(localDateTime: LocalDateTime): LocalDate {
        return localDateTime.plusHours(hoursAfterMidnightCounted).toLocalDate()
    }
}
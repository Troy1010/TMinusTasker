package com.tminus1010.tminustasker.domain

import com.tminus1010.tminustasker.all_layers.DaggerAppComponent
import org.junit.Assert.assertEquals
import org.junit.Test
import java.time.LocalDate
import java.time.LocalDateTime


class LocalDateTimeToLocalDateWithAdjustmentConverterTest {

    @Test
    fun convert() {
        // # Given
        val givenLocalDateTime = LocalDateTime.of(2023, 1, 2, 3, 0, 0)
        val expected = LocalDate.of(2023, 1, 1)
        DaggerAppComponent.builder()
            .build()
            .localDateTimeToLocalDateWithAdjustmentConverter()
            // # When
            .convert(givenLocalDateTime)
            // # Then
            .also { assertEquals(expected, it) }
    }
}
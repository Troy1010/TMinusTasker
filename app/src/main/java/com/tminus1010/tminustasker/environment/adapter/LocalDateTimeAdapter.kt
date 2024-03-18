package com.tminus1010.tminustasker.environment.adapter

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object LocalDateTimeAdapter {
    private val dateFormatter = DateTimeFormatter.ISO_DATE_TIME

    @ToJson
    fun toJson(x: LocalDateTime): String =
        x.format(dateFormatter)

    @FromJson
    fun fromJson(s: String): LocalDateTime =
        LocalDateTime.parse(s, dateFormatter)
}
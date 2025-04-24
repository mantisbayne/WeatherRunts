package com.mantisbayne.weatherrunts.utils

import java.time.Clock
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

class DateFormatter(private val clock: Clock = Clock.systemDefaultZone()) {

    fun formatToLocalTime(
        utcDateString: String? = null,
        shouldShowDayOfWeek: Boolean = false
    ): String {
        val pattern = if (shouldShowDayOfWeek) "EEE hh:mm a" else "hh:mm a"
        val formatter = DateTimeFormatter.ofPattern(pattern, Locale.getDefault())

        val zonedTime = if (utcDateString != null) {
            val inputFormatter = DateTimeFormatter.ISO_DATE_TIME
            val utcTime = LocalDateTime.parse(utcDateString, inputFormatter).atOffset(ZoneOffset.UTC)
            utcTime.atZoneSameInstant(clock.zone)
        } else {
            ZonedDateTime.now(clock)
        }

        return zonedTime.format(formatter)
    }

    fun getHourOfDay(utcDateString: String, toLocalTime: Boolean = true): Int {
        val formatter = DateTimeFormatter.ISO_DATE_TIME
        val utcTime = LocalDateTime.parse(utcDateString, formatter).atOffset(ZoneOffset.UTC)

        val zonedDateTime = if (toLocalTime) {
            utcTime.atZoneSameInstant(clock.zone)
        } else {
            utcTime.atZoneSameInstant(ZoneOffset.UTC)
        }

        return zonedDateTime.hour
    }
}

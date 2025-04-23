package com.mantisbayne.weatherrunts.utils

import android.os.Build
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.TimeZone

object DateUtils {

    fun formatToLocalTime(
        utcDateString: String? = null,
        shouldShowDayOfWeek: Boolean = false
    ): String {
        val pattern = if (shouldShowDayOfWeek) "EEE hh:mm a" else "hh:mm a"

        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val formatter = DateTimeFormatter.ofPattern(pattern, Locale.getDefault())

            val zonedTime = if (utcDateString != null) {
                val inputFormatter = DateTimeFormatter.ISO_DATE_TIME
                val utcTime = LocalDateTime.parse(utcDateString, inputFormatter)
                    .atOffset(ZoneOffset.UTC)
                utcTime.atZoneSameInstant(ZoneId.systemDefault())
            } else {
                ZonedDateTime.now()
            }

            zonedTime.format(formatter)

        } else {
            val sdf = SimpleDateFormat(pattern, Locale.getDefault())
            sdf.timeZone = TimeZone.getDefault()

            val date = if (utcDateString != null) {
                val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm", Locale.getDefault())
                inputFormat.timeZone = TimeZone.getTimeZone("UTC")
                inputFormat.parse(utcDateString)
            } else {
                Date()
            }

            sdf.format(date ?: Date())
        }
    }

    fun getHourOfDay(utcDateString: String, toLocalTime: Boolean = true): Int {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val formatter = DateTimeFormatter.ISO_DATE_TIME
            val utcTime = LocalDateTime.parse(utcDateString, formatter).atOffset(ZoneOffset.UTC)

            val zonedDateTime = if (toLocalTime) {
                utcTime.atZoneSameInstant(ZoneId.systemDefault())
            } else {
                utcTime.atZoneSameInstant(ZoneOffset.UTC)
            }

            zonedDateTime.hour
        } else {
            val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm", Locale.getDefault())
            inputFormat.timeZone = if (toLocalTime) TimeZone.getDefault() else TimeZone.getTimeZone("UTC")
            val date = inputFormat.parse(utcDateString)
            val calendar = Calendar.getInstance().apply {
                time = date ?: Date()
            }
            calendar.get(Calendar.HOUR_OF_DAY)
        }
    }
}

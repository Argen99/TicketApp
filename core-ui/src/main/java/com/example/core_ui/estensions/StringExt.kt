package com.example.core_ui.estensions

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.core_ui.utils.DateTimeFormatPatterns
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.Date
import java.util.Locale

fun String.formatDate(
    inputPattern: String = DateTimeFormatPatterns.ISO_PATTERN,
    outputPattern: String = DateTimeFormatPatterns.TIME_PATTERN
): String? {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val inputFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern(inputPattern)
        val outputFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern(outputPattern)
        val dateTime: LocalDateTime = LocalDateTime.parse(this, inputFormatter)
        dateTime.format(outputFormatter)
    } else {
        val inputFormatter = SimpleDateFormat(inputPattern, Locale.getDefault())
        val outputFormatter = SimpleDateFormat(outputPattern, Locale.getDefault())
        val date: Date? = inputFormatter.parse(this)
        date?.let { outputFormatter.format(it) }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
infix fun String.calculateFlightDurationTo(arrivalDateTime: String): Long {
    val formatter = DateTimeFormatter.ISO_DATE_TIME
    val departureDate = LocalDateTime.parse(this, formatter)
    val arrivalDate = LocalDateTime.parse(arrivalDateTime, formatter)
    val hoursBetween = ChronoUnit.HOURS.between(departureDate, arrivalDate)
    return hoursBetween
}
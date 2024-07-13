package com.example.core_ui.estensions

import android.os.Build
import com.example.core_ui.utils.DateTimeFormatPatterns
import java.text.SimpleDateFormat
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale

fun Date.toFormattedDateString(
    pattern: String = DateTimeFormatPatterns.DATE_WITH_DAY_PATTERN,
    locale: String = "ru"
): String {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val outputFormatter = DateTimeFormatter.ofPattern(pattern, Locale(locale))
        val localDate = this.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
        localDate.format(outputFormatter)
    } else {
        val outputFormatter = SimpleDateFormat(pattern, Locale(locale))
        outputFormatter.format(this)
    }
}

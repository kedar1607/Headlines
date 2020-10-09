package com.example.headlines.utils.extensions

import java.text.SimpleDateFormat
import java.util.*

/**
 * Converts the [Date] in to specified format with consideration of original timezone
 */
fun Date.formatTo(dateFormat: String, timeZone: TimeZone = TimeZone.getDefault()): String {
    val formatter = SimpleDateFormat(dateFormat, Locale.getDefault())
    formatter.timeZone = timeZone
    return formatter.format(this)
}
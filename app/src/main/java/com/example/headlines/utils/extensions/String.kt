package com.example.headlines.utils.extensions

import java.text.SimpleDateFormat
import java.util.*

/**
 * Converts the UTC date to specified format
 */
fun String.toDate(dateFormat: String = "yyyy-MM-dd HH:mm:ss", timeZone: TimeZone = TimeZone.getTimeZone("UTC")): String =
    SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").also { it.timeZone = timeZone }.parse(this).formatTo("MMM dd yyyy hh:mm aa")
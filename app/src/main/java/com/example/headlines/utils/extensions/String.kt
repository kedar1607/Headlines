package com.example.headlines.utils.extensions

import android.os.Build
import android.text.Html
import android.text.Spanned
import androidx.core.text.HtmlCompat
import java.text.SimpleDateFormat
import java.util.*

/**
 * Converts the UTC date to specified format
 */
fun String.toDate(timeZone: TimeZone = TimeZone.getTimeZone("UTC")): String =
    SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault()).also { it.timeZone = timeZone }.parse(this)?.passedTime().toString()

/**
 * Returns the HTML formatted text.
 */
fun String.getHTMLText(): Spanned{
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(this, HtmlCompat.TO_HTML_PARAGRAPH_LINES_INDIVIDUAL)
    }else{
        Html.fromHtml(this)
    }
}
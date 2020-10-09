package com.example.headlines.utils.extensions

import android.content.Context
import android.content.Intent

/**
 * Opens the native android share dialog with provided text.
 */
fun Context.share(content: String, title: CharSequence? = null) {
    val sendIntent: Intent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, content)
        type = "text/plain"
    }

    val shareIntent = Intent.createChooser(sendIntent, title)
    startActivity(shareIntent)
}
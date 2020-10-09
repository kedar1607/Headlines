package com.example.headlines.utils.extensions

import android.content.Context
import android.content.Intent
import android.net.Uri

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

/**
 * Opens the given url in a browser (Not native for the purpose of sample app)
 */
fun Context.openUrlInBrowser(url: String) {
    val browserIntent = Intent(Intent.ACTION_VIEW)
    browserIntent.data = Uri.parse(url)
    startActivity(browserIntent)
}
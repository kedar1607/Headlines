package com.example.headlines.utils.extensions

import android.text.format.DateUtils
import java.util.*

/**
 * This function returns the given [Date] object in to time lapsed format.
 * e.g. 5 minutes ago, 2 hours ago etc.
 */
fun Date.passedTime(): CharSequence =
    DateUtils.getRelativeTimeSpanString(this.time)

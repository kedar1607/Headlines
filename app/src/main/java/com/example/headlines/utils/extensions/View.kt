package com.example.headlines.utils.extensions

import android.view.View

/**
 * A little helper function that avoids couple of lines of code when it comes to showing/hiding the views using [View.getVisibility]
 */
fun View.setVisible(visible: Boolean) {
    visibility = if(visible) View.VISIBLE else View.GONE
}
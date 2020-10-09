package com.example.headlines.utils.extensions

import android.text.method.LinkMovementMethod
import android.text.util.Linkify
import android.widget.TextView

/**
 * This function essentially sets the visibility of the textview provided and linkfies the text provided with all ([Linkify.ALL]) the links it can possibly find in the text.
 * This also assigns the htmlText rather than regular text.
 * This function can further be extended to allow more functionality such as specific links, regular text etc.
 */
fun TextView.setTexViewVisibleWithHtmlLinks(contentText: String?){
    setVisible((!contentText.isNullOrBlank()).also {notNullOrBlank ->
        if(notNullOrBlank) {
            text = contentText?.getHTMLText()
            movementMethod = LinkMovementMethod.getInstance()
            Linkify.addLinks(this, Linkify.ALL)
        }
    })
}
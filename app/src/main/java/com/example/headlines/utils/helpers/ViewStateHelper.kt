package com.example.headlines.utils.helpers

import android.view.View
import com.example.headlines.utils.extensions.setVisible

/**
 * This is just the helper class that is useful to switch amongst the three different states viz. loading, content and error
 * Note: Just for the simplicity of this sample app, I chose to include only three different states and not worry about any other state.
 */
class ViewStateHelper(private val contentView: View, private val errorView: View, private val loadingView: View) {
    /**
     * Shows loading view.
     */
    fun showLoading(){
        contentView.setVisible(false)
        errorView.setVisible(false)
        loadingView.setVisible(true)
    }

    /**
     * Shows error view.
     */
    fun showError(){
        contentView.setVisible(false)
        errorView.setVisible(true)
        loadingView.setVisible(false)
    }

    /**
     * shows content view.
     */
    fun showContent(){
        contentView.setVisible(true)
        errorView.setVisible(false)
        loadingView.setVisible(false)
    }
}
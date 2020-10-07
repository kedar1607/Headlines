package com.example.headlines.base

import androidx.lifecycle.LiveData

/**
 * Base class for every single Repository in the app.
 * Takes care of the background work, exceptions, and separate job exceptions.
 */
interface BaseRepo<T> {

    /**
     * Forces us to implement a data source property where we will be posting our values
     * to be observed in the ViewModels.
     */
    val dataSource: LiveData<T>

}
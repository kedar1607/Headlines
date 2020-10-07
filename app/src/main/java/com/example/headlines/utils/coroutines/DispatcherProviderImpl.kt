package com.example.headlines.utils.coroutines

import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

/**
 * This class is the implementation of real [DispatcherProvider] that provides real [kotlinx.coroutines.CoroutineDispatcher]
 * to launch coroutines.
 */
class DispatcherProviderImpl : DispatcherProvider {
    override val io: CoroutineContext = Dispatchers.IO
    override val main: CoroutineContext = Dispatchers.Main
    override val default: CoroutineContext = Dispatchers.Default
}
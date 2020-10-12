package com.example.headlines.testutils

import com.example.headlines.utils.coroutines.DispatcherProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlin.coroutines.CoroutineContext

class TestDispatcherProvider(
    val testDispatcher: CoroutineDispatcher
) : DispatcherProvider {
    override val io: CoroutineContext = testDispatcher
    override val main: CoroutineContext = testDispatcher
    override val default: CoroutineContext = testDispatcher
}
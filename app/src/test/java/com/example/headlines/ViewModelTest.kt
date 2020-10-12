package com.example.headlines

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.rules.TestRule

/**
 * Base class for all view model unit tests. The only reason for this base class is to avoid the boilerplate code.
 */
@ExperimentalCoroutinesApi
open class ViewModelTest : BaseTest() {
    @Rule
    @JvmField
    val rule: TestRule = InstantTaskExecutorRule()
}
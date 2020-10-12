package com.example.headlines

import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

/**
 * Any tests should have mockito initializations + paused coroutine dispatchers when they are started.
 * Also, once tests are done running, we need to call [TestCoroutineDispatcher.cleanupTestCoroutines].
 */
@ExperimentalCoroutinesApi
open class BaseTest {

    @Rule
    @JvmField
    var coroutinesTestRule = CoroutinesTestRule()

    @Before
    open fun setUp() {
        MockitoAnnotations.initMocks(this)
        coroutinesTestRule.testDispatcher.pauseDispatcher()
    }

    /**
     * See [Memory leak in mockito-inline...](https://github.com/mockito/mockito/issues/1614)
     */
    @After
    open fun tearDown() {
        coroutinesTestRule.testDispatcher.cleanupTestCoroutines()
        Mockito.framework().clearInlineMocks()
    }

}
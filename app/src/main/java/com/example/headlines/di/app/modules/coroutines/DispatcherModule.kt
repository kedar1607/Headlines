package com.example.headlines.di.app.modules.coroutines

import com.example.headlines.utils.coroutines.DispatcherProvider
import com.example.headlines.utils.coroutines.DispatcherProviderImpl
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers

/**
 * This module is responsible for providing the right [DispatcherProvider] based on what application (real or test)
 * is being currently used. In most tests we do not use the [Dispatchers.IO], [Dispatchers.Main] or [Dispatchers.Default].
 * Instead, most tests use the [kotlinx.coroutines.test.TestCoroutineDispatcher]. This way we would be able to mock the real dispatchers with [kotlinx.coroutines.test.TestCoroutineDispatcher]
 */
@Module
class DispatcherModule {
    @Provides
    fun provideDispatcherProvider(): DispatcherProvider {
        return DispatcherProviderImpl()
    }
}
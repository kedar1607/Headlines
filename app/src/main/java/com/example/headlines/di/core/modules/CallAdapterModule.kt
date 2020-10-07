package com.example.headlines.di.core.modules

import com.example.headlines.di.scopes.CoreScope
import com.example.headlines.network.CallAdapterFactory
import dagger.Module
import dagger.Provides
import retrofit2.CallAdapter

/**
 * This module provides the custom call adapter factory for retrofit module.
 */
@Module
class CallAdapterModule {
    @Provides
    @CoreScope
    fun provideCallAdapterFactory(): CallAdapter.Factory =
        CallAdapterFactory()
}
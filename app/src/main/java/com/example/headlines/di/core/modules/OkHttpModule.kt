package com.example.headlines.di.core.modules

import com.example.headlines.di.scopes.CoreScope
import com.example.headlines.network.APIKeyInterceptor
import com.example.headlines.utils.network.OkHttpBuilder.getOkHttpClientBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient

/**
 * This module provides the custom [OkHttpClient] for the retrofit module.
 * Why we need custom [OkHttpClient] - Checkout [APIKeyInterceptor]
 */
@Module
class OkHttpModule {
    @Provides
    @CoreScope
    fun provideOkHttpClient(): OkHttpClient {
        val clientBuilder = getOkHttpClientBuilder()

        return clientBuilder
            .addInterceptor(APIKeyInterceptor())
            .build()
    }

}
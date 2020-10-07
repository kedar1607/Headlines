package com.example.headlines.di.core.modules


import com.example.headlines.di.scopes.CoreScope
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import com.example.headlines.BuildConfig
import retrofit2.CallAdapter
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * This module provides the [Retrofit] with all the required custom components injected in it e.g. [OkHttpClient] and [CallAdapter.Factory]
 */
@Module
class RetrofitModule {
    @Provides
    @CoreScope
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        callAdapterFactory: CallAdapter.Factory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addCallAdapterFactory(callAdapterFactory)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(okHttpClient).build()
    }
}
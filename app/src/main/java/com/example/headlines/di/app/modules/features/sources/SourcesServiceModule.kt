package com.example.headlines.di.app.modules.features.sources

import com.example.headlines.BuildConfig
import com.example.headlines.di.scopes.FeatureScope
import com.example.headlines.services.SourcesService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

/**
 * This module provides the [SourcesService] which is needed to make the sources network call.
 * This is also scoped to a feature. This means that any dependencies provided by this module will not live outside of encompassing [SourcesActivityModule].
 */
@Module
class SourcesServiceModule {

    @Provides
    @FeatureScope
    internal fun provideSourcesService(
        retrofit: Retrofit
    ): SourcesService {
        return retrofit
            .newBuilder()
            .baseUrl("${BuildConfig.BASE_URL}/sources/")
            .build().create(SourcesService::class.java)
    }

}
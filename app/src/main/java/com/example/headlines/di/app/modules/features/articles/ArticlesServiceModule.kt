package com.example.headlines.di.app.modules.features.articles

import com.example.headlines.BuildConfig
import com.example.headlines.di.app.modules.features.sources.SourcesActivityModule
import com.example.headlines.di.scopes.FeatureScope
import com.example.headlines.features.articles.services.ArticlesService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

/**
 * This module provides the [ArticlesService] which is needed to make the articles (BASE_URL/top-headlines/) network call.
 * This is also scoped to a feature. This means that any dependencies provided by this module will not live outside of encompassing [SourcesActivityModule].
 */
@Module
class ArticlesServiceModule {

    @Provides
    @FeatureScope
    internal fun provideArticlesService(
        retrofit: Retrofit
    ): ArticlesService {
        return retrofit
            .newBuilder()
            .baseUrl("${BuildConfig.BASE_URL}/top-headlines/")
            .build().create(ArticlesService::class.java)
    }

}
package com.example.headlines.di.app.modules.features.articles


import com.example.headlines.BuildConfig
import com.example.headlines.di.scopes.FeatureScope
import com.example.headlines.features.articles.services.ArticlesService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

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
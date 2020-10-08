package com.example.headlines.di.app.modules.features.articles

import com.example.headlines.di.scopes.FeatureScope
import com.example.headlines.features.articles.activities.ArticlesActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * This is the sources activity module. Basically any dependency required by [ArticlesActivity]
 * and any of it's components is provided from here.
 * Note - Any additional dependency that the ones provided in [com.example.headlines.di.core.CoreComponent] and [com.example.headlines.di.app.AppComponent].
 */
@Module
abstract class ArticlesActivityModule {
    /**
     * This is scoped to a feature. This basically means that any dependency that's created for [ArticlesActivity]
     * will live only within [ArticlesActivity].
     */
    @FeatureScope
    @ContributesAndroidInjector(
        modules = [
            ArticlesViewModelsModule::class,
            ArticlesFragmentsModule::class,
            ArticlesServiceModule::class]
    )
    abstract fun contributesArticlesActivity(): ArticlesActivity
}
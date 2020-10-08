package com.example.headlines.di.app.modules.features.articles

import com.example.headlines.features.articles.fragments.ArticleDetailFragment
import com.example.headlines.features.articles.fragments.ArticlesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * This modules provides all the required fragments in [ArticlesActivityModule].
 * Scope of all of these fragments and the dependencies within then is restricted to the scope of [ArticlesActivityModule]
 * which is [com.example.headlines.di.scopes.FeatureScope].
 */
@Module
abstract class ArticlesFragmentsModule {
    @ContributesAndroidInjector
    abstract fun contributeArticlesFragment(): ArticlesFragment

    @ContributesAndroidInjector
    abstract fun contributeArticleDetailFragment(): ArticleDetailFragment
}
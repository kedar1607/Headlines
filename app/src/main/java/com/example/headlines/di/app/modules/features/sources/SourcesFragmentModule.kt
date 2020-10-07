package com.example.headlines.di.app.modules.features.sources

import com.example.headlines.sources.fragments.SourcesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * This modules provides all the required fragments in [SourcesActivityModule].
 * Scope of all of these fragments and the dependencies within then is restricted to the scope of [SourcesActivityModule]
 * which is [com.example.headlines.di.scopes.FeatureScope].
 */
@Module
abstract class SourcesFragmentModule {
    @ContributesAndroidInjector
    abstract fun contributeSourcesFragment(): SourcesFragment
}
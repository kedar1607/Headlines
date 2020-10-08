package com.example.headlines.di.app.modules.features.sources

import com.example.headlines.di.scopes.FeatureScope
import com.example.headlines.features.sources.activities.SourcesActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * This is the sources activity module. Basically any dependency required by [SourcesActivity] and any of it's components is provided from here.
 * Note - Any additional dependency that the ones provided in [com.example.headlines.di.core.CoreComponent] and [com.example.headlines.di.app.AppComponent].
 */
@Module
abstract class SourcesActivityModule {
    /**
     * This is scoped to a feature. This basically means that any dependency that's created for [SourcesActivityModule] will live only within [SourcesServiceModule].
     */
    @FeatureScope
    @ContributesAndroidInjector(
        modules = [
            SourcesServiceModule::class,
            SourcesFragmentModule::class,
            SourcesViewModelModule::class]
    )
    abstract fun contributesSourcesActivity(): SourcesActivity
}
package com.example.headlines.di.app

import android.content.Context
import com.example.headlines.application.HeadlinesApplication
import com.example.headlines.di.app.modules.coroutines.DispatcherModule
import com.example.headlines.di.app.modules.features.articles.ArticlesActivityModule
import com.example.headlines.di.app.modules.features.sources.SourcesActivityModule
import com.example.headlines.di.app.modules.viewmodels.ViewModelFactoryModule
import com.example.headlines.di.core.CoreComponent
import com.example.headlines.di.scopes.AndroidScope
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector

/**
 * List of larger Modules that include SubComponents with Activities and Fragments.
 */
@AndroidScope
@Component(
    modules = [
        AndroidInjectionModule::class,
        DispatcherModule::class,
        ViewModelFactoryModule::class,
        SourcesActivityModule::class,
        ArticlesActivityModule::class],
    dependencies = [CoreComponent::class]
)
interface AppComponent : AndroidInjector<HeadlinesApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Context): Builder

        fun coreComponent(coreComponent: CoreComponent): Builder

        fun build(): AppComponent
    }

}
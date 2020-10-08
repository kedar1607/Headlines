package com.example.headlines.di.app.modules.features.articles

import androidx.lifecycle.ViewModel
import com.example.headlines.di.scopes.ViewModelKey
import com.example.headlines.features.articles.viewmodels.ArticlesDestinationViewModel
import com.example.headlines.features.articles.viewmodels.ArticlesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * This is a view model module that provides necessary view models required within [ArticlesActivityModule] and its components (or sub-components).
 * Scope of all of these fragments and the dependencies within then is restricted to the scope of [ArticlesActivityModule]
 * which is [com.example.headlines.di.scopes.FeatureScope].
 */
@Module
abstract class ArticlesViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(ArticlesViewModel::class)
    protected abstract fun articlesViewModel(articlesViewModel: ArticlesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ArticlesDestinationViewModel::class)
    protected abstract fun articlesDestinationViewModel(articlesDestinationViewModel: ArticlesDestinationViewModel): ViewModel

}
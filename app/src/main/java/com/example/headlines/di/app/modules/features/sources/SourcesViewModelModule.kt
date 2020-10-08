package com.example.headlines.di.app.modules.features.sources

import androidx.lifecycle.ViewModel
import com.example.headlines.di.scopes.ViewModelKey
import com.example.headlines.features.sources.viewmodels.SourcesDestinationViewModel
import com.example.headlines.features.sources.viewmodels.SourcesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * This is a view model module that provides necessary view models required within [SourcesActivityModule] and its components (or sub-components).
 * Scope of all of these fragments and the dependencies within then is restricted to the scope of [SourcesActivityModule]
 * which is [com.example.headlines.di.scopes.FeatureScope].
 */
@Module
abstract class SourcesViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(SourcesViewModel::class)
    protected abstract fun sourcesViewModel(sourcesViewModel: SourcesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SourcesDestinationViewModel::class)
    protected abstract fun sourcesDestinationViewModel(sourcesDestinationViewModel: SourcesDestinationViewModel): ViewModel

}
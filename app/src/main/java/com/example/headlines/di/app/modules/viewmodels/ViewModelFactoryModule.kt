package com.example.headlines.di.app.modules.viewmodels

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module

/**
 * This module provides the [ViewModelFactory] to all of the components (and sub-components) that need to use it for generating VMs.
 */
@Module
abstract class ViewModelFactoryModule {
    @Binds
    abstract fun provideViewModelFactory(
        factory: ViewModelFactory
    ): ViewModelProvider.Factory
}
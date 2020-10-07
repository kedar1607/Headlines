package com.example.headlines.di.scopes

import javax.inject.Scope

/**
 * Annotates lifecycle for SubComponents. It's used to guarantee that some
 * of the inner dependencies of Activities, Fragments, Services may be scoped to them,
 * e.g. it's used for dependencies that need to have a single instance
 * for the entire lifecycle of this scope.
 *
 * If a Fragment is injected with two ViewModels but both of them need to have exactly the same
 * instance of a dependency -> use this annotation for the Module where you have this fragment
 * and a for the specific dependency that you need a single instance of.
 */
@Scope
@Retention
annotation class FeatureScope
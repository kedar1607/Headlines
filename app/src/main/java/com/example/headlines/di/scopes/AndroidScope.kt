package com.example.headlines.di.scopes

import javax.inject.Scope

/**
 * Annotates Components and dependencies that live throughout the lifecycle
 * of the entire lifecycle of a place where they are getting injected:
 * AppComponent (AndroidScope) depends on CoreComponent (CoreScope) so the lifecycle of the CoreComponent
 * should be different and may be longer.
 * Currently they are the same, since they are getting injected when we create the app.
 */
@Scope
@Retention
annotation class AndroidScope
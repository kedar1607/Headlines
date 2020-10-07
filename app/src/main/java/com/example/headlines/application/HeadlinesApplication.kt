package com.example.headlines.application

import android.app.Application
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.*
import com.example.headlines.di.app.DaggerAppComponent
import com.example.headlines.di.core.DaggerCoreComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject
import kotlin.properties.Delegates

/**
 * This is the application class used to initialize all of the application level dagger components in the project.
 */
@VisibleForTesting
open class HeadlinesApplication : Application(), HasAndroidInjector, LifecycleObserver {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>


    override fun androidInjector(): AndroidInjector<Any> {
        return androidInjector
    }

    open var appDaggerComponent: AndroidInjector<HeadlinesApplication>? = null
        set(value) {
            field = value
            appDaggerComponent?.inject(this)
        }

    open fun getAppComponent(): AndroidInjector<HeadlinesApplication> {
        val coreComponent = DaggerCoreComponent.builder().application(this).build()
        return DaggerAppComponent.builder()
            .application(this)
            .coreComponent(coreComponent)
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        ProcessLifecycleOwner.get().lifecycle.addObserver(this)
        appDaggerComponent = getAppComponent()
    }

    companion object {
        var instance: HeadlinesApplication by Delegates.notNull()
    }

}
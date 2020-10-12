package com.example.headlines.di.core

import android.content.Context
import com.example.headlines.database.AppDatabase
import com.example.headlines.di.core.modules.CallAdapterModule
import com.example.headlines.di.core.modules.OkHttpModule
import com.example.headlines.di.core.modules.RetrofitModule
import com.example.headlines.di.core.modules.RoomDatabaseModule
import com.example.headlines.di.scopes.CoreScope
import dagger.BindsInstance
import dagger.Component
import retrofit2.Retrofit

/**
 * Here we only have components that need to be available for every single place in the app.
 */
@CoreScope
@Component(
    modules = [
        OkHttpModule::class,
        CallAdapterModule::class,
        RetrofitModule::class,
        RoomDatabaseModule::class
    ]
)
interface CoreComponent {

    fun provideRetrofit(): Retrofit

    fun provideRoomDatabase(): AppDatabase

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Context): Builder

        fun build(): CoreComponent

    }

}
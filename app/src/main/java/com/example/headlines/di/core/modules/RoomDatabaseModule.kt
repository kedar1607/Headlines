package com.example.headlines.di.core.modules

import android.content.Context
import androidx.room.Room
import com.example.headlines.database.AppDatabase
import com.example.headlines.di.scopes.CoreScope
import dagger.Module
import dagger.Provides

/**
 * This module provides the [AppDatabase] required by room in order to carry out all the database related operations.
 */
@Module
class RoomDatabaseModule {
    @Provides
    @CoreScope
    fun provideRoomDatabase(
        applicationContext: Context
    ): AppDatabase {
        return Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "headlines-db"
        ).build()
    }
}
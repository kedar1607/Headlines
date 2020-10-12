package com.example.headlines.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.headlines.features.sources.dao.SourcesDao
import com.example.headlines.features.sources.models.Source

/**
 * App database component that builds the room database that can be injected anywhere in the app (Since the module is scoped to [@CoreScope])
 */
@Database(entities = [Source::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun sourcesDao(): SourcesDao
}
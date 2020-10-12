package com.example.headlines.features.sources.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.headlines.features.sources.models.Source

/**
 * This is the DAO component of the room database for [Source] table.
 */
@Dao
interface SourcesDao {
    @Query("SELECT * FROM sources")
    fun getAll(): LiveData<List<Source>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(sources: List<Source>)

    @Query("SELECT * FROM sources")
    fun deleteLast(): LiveData<List<Source>>
}
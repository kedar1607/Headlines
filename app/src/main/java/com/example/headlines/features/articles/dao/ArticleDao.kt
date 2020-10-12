package com.example.headlines.features.articles.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.headlines.features.articles.models.Article

/**
 * This is the DAO component of the room database for [Article] table.
 */
@Dao
interface ArticleDao {
    @Query("SELECT * FROM articles where id like :sourceId")
    fun getAll(sourceId: String): LiveData<List<Article>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(sources: List<Article>)
}
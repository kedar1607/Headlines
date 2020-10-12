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
    @Query("SELECT * FROM articles where id like :sourceId order by publishedAt desc")
    fun getAll(sourceId: String): LiveData<List<Article>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(sources: List<Article>)

    @Query("SELECT COUNT() FROM articles where id like :sourceId")
    fun getRowCount(sourceId: String): Int

    @Query("DELETE FROM articles where article_url NOT IN (SELECT article_url from articles ORDER BY publishedAt DESC LIMIT 10)")
    fun deleteLast()
}
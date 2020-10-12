package com.example.headlines.features.articles.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.headlines.features.sources.models.Source
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

/**
 * This model class that represents an article on the articles screen.
 */
@JsonClass(generateAdapter = true)
@Parcelize
@Entity(tableName = "articles")
data class Article(
    @Json(name = "author")
    @ColumnInfo(name = "author")
    val author: String? = null,

    @Json(name = "content")
    @ColumnInfo(name = "content")
    val content: String? = null,

    @Json(name = "description")
    @ColumnInfo(name = "article_description")
    val description: String? = null,

    @Json(name = "publishedAt")
    @ColumnInfo(name = "publishedAt")
    val publishedAt: String,

    @Json(name = "source")
    @Embedded val source: Source,

    @Json(name = "title")
    @ColumnInfo(name = "title")
    val title: String,

    @Json(name = "url")
    @ColumnInfo(name = "article_url")
    @PrimaryKey val url: String,

    @Json(name = "urlToImage")
    @ColumnInfo(name = "urlToImage")
    val urlToImage: String? = null
) : Parcelable
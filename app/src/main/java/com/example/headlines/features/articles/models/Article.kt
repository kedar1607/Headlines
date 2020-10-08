package com.example.headlines.features.articles.models

import com.example.headlines.features.sources.models.Source
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * This model class that represents an article on the articles screen.
 */
@JsonClass(generateAdapter = true)
data class Article(
    @Json(name = "author")
    val author: String? = null,
    @Json(name = "content")
    val content: String? = null,
    @Json(name = "description")
    val description: String? = null,
    @Json(name = "publishedAt")
    val publishedAt: String,
    @Json(name = "source")
    val source: Source,
    @Json(name = "title")
    val title: String,
    @Json(name = "url")
    val url: String,
    @Json(name = "urlToImage")
    val urlToImage: String? = null
)
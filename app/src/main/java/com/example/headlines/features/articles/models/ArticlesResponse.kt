package com.example.headlines.features.articles.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * This is the model class used to represent the response that is received in the form of json from the articles api call.
 * [articles] is the list of [Article] that will be displayed on the articles screen.
 * [totalResults] is the number of articles for the specific network call.
 */
@JsonClass(generateAdapter = true)
data class ArticlesResponse(
    @Json(name = "articles")
    val articles: List<Article>,
    @Json(name = "status")
    val status: String,
    @Json(name = "totalResults")
    val totalResults: Int
)
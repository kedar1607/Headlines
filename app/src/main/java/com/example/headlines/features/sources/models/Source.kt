package com.example.headlines.features.sources.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Model class for sources shown in the source fragment.
 */
@JsonClass(generateAdapter = true)
data class Source(
    @Json(name = "category")
    val category: String? = null,
    @Json(name = "country")
    val country: String? = null,
    @Json(name = "description")
    val description: String? = null,
    @Json(name = "id")
    val id: String? = null,
    @Json(name = "language")
    val language: String? = null,
    @Json(name ="name")
    val name: String,
    @Json(name = "url")
    val url: String? = null
)
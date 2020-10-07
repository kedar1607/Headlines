package com.example.headlines.models.source

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * API response for getAllSources call made using [com.example.headlines.services.SourcesService]
 */
@JsonClass(generateAdapter = true)
data class SourcesResponse(
    @Json(name = "sources")
    val sources: List<Source>,
    @Json(name = "status")
    val status: String
)
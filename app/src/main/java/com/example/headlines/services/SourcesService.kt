package com.example.headlines.services

import com.example.headlines.network.APIResult
import com.example.headlines.models.source.SourcesResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * This service is used for fetching the list of all sources.
 */
interface SourcesService {
    /**
     * default args are provided
     * [country] US
     * [language] English
     * returns [APIResult] wrapper of the Retrofit Call response.
     */
    @GET(".")
    suspend fun getAllSources(@Query("country") country: String = "us", @Query("language") language: String = "en"): APIResult<SourcesResponse>
}
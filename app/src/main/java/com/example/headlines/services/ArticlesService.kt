package com.example.headlines.services

import com.example.headlines.models.article.ArticlesResponse
import com.example.headlines.network.APIResult
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * This service is used to load articles based on source selection / no source selection
 */
interface ArticlesService {
    /**
     * Fetches list of all the articles with no source filter. [country] is by default "US"
     * returns [APIResult] wrapper of the Retrofit Call response.
     */
    @GET(".")
    suspend fun getArticles(@Query("country") country: String = "us"): APIResult<ArticlesResponse>

    /**
     * Fetches the list of articles based on primarily [source] (provided by the caller), [country] (default US), [language] (default English)
     * returns [APIResult] wrapper of the Retrofit Call response.
     */
    @GET(".")
    suspend fun getArticlesBySource(
        @Query("country") country: String = "us",
        @Query("sources") source: String,
        @Query("language") language: String = "en"
    ): APIResult<ArticlesResponse>
}
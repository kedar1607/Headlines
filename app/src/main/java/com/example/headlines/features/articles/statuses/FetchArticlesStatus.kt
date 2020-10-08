package com.example.headlines.features.articles.statuses

import com.example.headlines.features.articles.models.ArticlesResponse
import com.example.headlines.network.APIResult

/**
 * This class represents the status of the fetching articles from [com.example.headlines.features.articles.services.ArticlesService.getArticlesBySource].
 * Each of the subclasses (or objects) of this sealed class represents the state of the API call made in [com.example.headlines.features.articles.services.ArticlesService].
 */
sealed class FetchArticlesStatus {

    /**
     * This indicates that the [com.example.headlines.features.articles.services.ArticlesService.getArticlesBySource] was successful.
     * [articles] is the actual response that is received.
     */
    class Success(val articles: ArticlesResponse): FetchArticlesStatus()

    /**
     * This essentially means that the [com.example.headlines.features.articles.services.ArticlesService.getArticlesBySource] failed and we update UI with
     * the appropriate view.
     */
    object Failure: FetchArticlesStatus()

    companion object {
        /**
         * This function basically converts the [APIResult], a wrapper of a retrofit Call<T> in to [FetchArticlesStatus]
         * which is of one of the two types [Success] and [Failure]
         */
        fun getFetchArticlesStatusFromApiResult(result: APIResult<ArticlesResponse>): FetchArticlesStatus {
            return when {
                result is APIResult.Success && result.data != null -> Success(result.data)
                else -> Failure
            }
        }
    }
}
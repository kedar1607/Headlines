package com.example.headlines.features.sources.statuses

import com.example.headlines.features.sources.models.SourcesResponse
import com.example.headlines.network.APIResult

/**
 * This class represents the status of the fetching sources from [com.example.headlines.features.sources.repositories.SourcesRepository].
 * Each of the subclasses (or objects) of this sealed class represents the state of the API call made in [com.example.headlines.features.sources.services.SourcesService].
 */
sealed class FetchSourcesStatus {

    /**
     * This indicates that the [com.example.headlines.features.sources.services.SourcesService.getAllSources] was successful.
     * [sources] is the actual response that is received.
     */
    class Success(val sources: SourcesResponse): FetchSourcesStatus()

    /**
     * This essentially means that the [com.example.headlines.features.sources.services.SourcesService.getAllSources] failed and we update UI with
     * the appropriate view.
     * Note : We can technically check here if it's a connection problem and determine whether to show the local data (from room DB) in that specific case.
     * But for the purpose of this demo, I decided to keep it simple.
     */
    object Failure: FetchSourcesStatus()

    companion object {
        /**
         * This function basically converts the [APIResult], a wrapper of a retrofit Call<T> in to [FetchSourcesStatus]
         * which is of one of the two types [Success] and [Failure]
         */
        fun getFetchSourcesStatusFromApiResult(result: APIResult<SourcesResponse>): FetchSourcesStatus {
            return when {
                result is APIResult.Success && result.data != null -> Success(result.data)
                else -> Failure
            }
        }
    }
}
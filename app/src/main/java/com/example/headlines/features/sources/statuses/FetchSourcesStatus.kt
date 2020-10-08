package com.example.headlines.features.sources.statuses

import com.example.headlines.network.APIResult
import com.example.headlines.features.sources.models.SourcesResponse

/**
 * This class represents the status of the fetching sources from [com.example.headlines.sources.repositories.SourcesRepository].
 * Each of the subclasses (or objects) of this sealed class represents the state of the API call made in [com.example.headlines.services.SourcesService].
 */
sealed class FetchSourcesStatus {

    /**
     * This indicates that the [com.example.headlines.services.SourcesService.getAllSources] was successful.
     * [sources] is the actual response that is received.
     */
    class Success(val sources: SourcesResponse): FetchSourcesStatus()

    /**
     * This essentially means that the [com.example.headlines.services.SourcesService.getAllSources] failed and we update UI with
     * the appropriate view.
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
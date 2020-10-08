package com.example.headlines.features.sources.repositories

import androidx.lifecycle.MediatorLiveData
import com.example.headlines.base.BaseRepo
import com.example.headlines.features.sources.services.SourcesService
import com.example.headlines.features.sources.statuses.FetchSourcesStatus
import javax.inject.Inject

/**
 * This repository is responsible for making [fetchAllSources] call in order to fetch sources for populating them on the source fragment.
 */
class SourcesRepository @Inject constructor(
    private val sourcesService: SourcesService
) : BaseRepo<FetchSourcesStatus> {

    override val dataSource: MediatorLiveData<FetchSourcesStatus> = MediatorLiveData()

    suspend fun fetchAllSources() {
        val apiResult = sourcesService.getAllSources()
        dataSource.postValue(FetchSourcesStatus.getFetchSourcesStatusFromApiResult(apiResult))
    }
}
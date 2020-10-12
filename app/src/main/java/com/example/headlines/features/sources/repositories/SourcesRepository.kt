package com.example.headlines.features.sources.repositories

import androidx.lifecycle.MediatorLiveData
import com.example.headlines.base.BaseRepo
import com.example.headlines.database.AppDatabase
import com.example.headlines.features.sources.services.SourcesService
import com.example.headlines.features.sources.statuses.FetchSourcesStatus
import javax.inject.Inject

/**
 * This repository is responsible for making [fetchAllSources] call in order to fetch sources for populating them on the source fragment.
 */
class SourcesRepository @Inject constructor(
    /**
     * @Remote Data source
     */
    private val sourcesService: SourcesService,
    /**
     * @Local Data source
     */
    private val appDatabase: AppDatabase
) : BaseRepo<FetchSourcesStatus> {

    /**
     * Remote data source
     */
    override val dataSource: MediatorLiveData<FetchSourcesStatus> = MediatorLiveData()

    suspend fun fetchAllSources() {
        val apiResult = sourcesService.getAllSources()
        FetchSourcesStatus.getFetchSourcesStatusFromApiResult(apiResult).also {
            /**
             * In case of success, we do not post the value from the remote data source. Instead, we push the data received from the remote api call in to the db (localSource).
             * view model is already listening through the [localSource], hence it will get an update when insert is happening.
             */
            if (it is FetchSourcesStatus.Success) {
                appDatabase.sourcesDao().insertAll(it.sources.sources)
            }else{
                dataSource.postValue(it)
            }
        }
    }

    /**
     * Local data source to pull data from the db.
     */
    fun localSource() =
        appDatabase.sourcesDao().getAll()
}
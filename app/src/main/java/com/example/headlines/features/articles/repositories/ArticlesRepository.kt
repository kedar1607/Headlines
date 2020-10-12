package com.example.headlines.features.articles.repositories

import androidx.lifecycle.MediatorLiveData
import com.example.headlines.base.BaseRepo
import com.example.headlines.database.AppDatabase
import com.example.headlines.features.articles.services.ArticlesService
import com.example.headlines.features.articles.statuses.FetchArticlesStatus
import javax.inject.Inject

/**
 * This repository is responsible for making [fetchArticlesBySourceId] call in order to fetch articles
 * for populating them in the [com.example.headlines.features.articles.fragments.ArticlesFragment]
 */
class ArticlesRepository @Inject constructor(
    private val articlesService: ArticlesService,
    private val appDatabase: AppDatabase
) : BaseRepo<FetchArticlesStatus> {

    override val dataSource: MediatorLiveData<FetchArticlesStatus> = MediatorLiveData()

    suspend fun fetchArticlesBySourceId(sourceId: String) {
        val apiResult = articlesService.getArticlesBySource(source = sourceId)
        FetchArticlesStatus.getFetchArticlesStatusFromApiResult(apiResult).also {
            /**
             * In case of success, we do not post the value from the remote data source. Instead, we push the data received from the remote api call in to the db (localSource).
             * view model is already listening through the [localSource], hence it will get an update when insert is happening.
             */
            if (it is FetchArticlesStatus.Success) {
                /**
                 * This is an eviction policy so that we do not have more than 20 elements in the list
                 * at one time. The number is set to 10.
                 */
                val size  = appDatabase.articlesDao().getRowCount(sourceId)
                if(size > 10){
                    appDatabase.articlesDao().deleteLast()
                }
                appDatabase.articlesDao().insertAll(it.articles.articles)
            }else{
                dataSource.postValue(it)
            }
        }
        dataSource.postValue(FetchArticlesStatus.getFetchArticlesStatusFromApiResult(apiResult))
    }

    /**
     * Local data source to pull data from the db.
     * [sourceId] filter by source
     */
    fun localSource(sourceId: String) =
        appDatabase.articlesDao().getAll(sourceId)
}
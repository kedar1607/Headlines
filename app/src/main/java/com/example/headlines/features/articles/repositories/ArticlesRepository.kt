package com.example.headlines.features.articles.repositories

import androidx.lifecycle.MediatorLiveData
import com.example.headlines.base.BaseRepo
import com.example.headlines.features.articles.services.ArticlesService
import com.example.headlines.features.articles.statuses.FetchArticlesStatus
import javax.inject.Inject

/**
 * This repository is responsible for making [fetchAllSources] call in order to fetch sources for populating them on the source fragment.
 */
class ArticlesRepository @Inject constructor(
    private val articlesService: ArticlesService
) : BaseRepo<FetchArticlesStatus> {

    override val dataSource: MediatorLiveData<FetchArticlesStatus> = MediatorLiveData()

    suspend fun fetchArticlesBySourceId(sourceId: String) {
        val apiResult = articlesService.getArticlesBySource(source = sourceId)
        dataSource.postValue(FetchArticlesStatus.getFetchArticlesStatusFromApiResult(apiResult))
    }
}
package com.example.headlines.features.articles.repositories

import androidx.lifecycle.MediatorLiveData
import com.example.headlines.base.BaseRepo
import com.example.headlines.features.articles.services.ArticlesService
import com.example.headlines.features.articles.statuses.FetchArticlesStatus
import javax.inject.Inject

/**
 * This repository is responsible for making [fetchArticlesBySourceId] call in order to fetch articles
 * for populating them in the [com.example.headlines.features.articles.fragments.ArticlesFragment]
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
package com.example.headlines.features.articles.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.headlines.features.articles.repositories.ArticlesRepository
import com.example.headlines.features.articles.states.ArticleScreenState
import com.example.headlines.features.articles.statuses.FetchArticlesStatus
import com.example.headlines.utils.coroutines.DispatcherProvider
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * This view model is responsible for making the fetch call through [repository].
 * Basically [_articlesScreenState] or publicly available [articlesScreenState] is used to track the actual state of the UI/ View (Fragment)
 * Everything happens when the VM is initialized.
 * dispatcherProvider is used to launch the coroutine in that specific [kotlinx.coroutines.CoroutineDispatcher].
 * Please read [com.example.headlines.utils.coroutines.DispatcherProviderImpl] for more details on how these dispatchers are provided.
 */
class ArticlesViewModel @Inject constructor(private val repository: ArticlesRepository, private val dispatcherProvider: DispatcherProvider): ViewModel(){

    /**
     * This live data represents the overall state of the fragment that is attached to this vm
     */
    private val _articlesScreenState: MediatorLiveData<ArticleScreenState> = MediatorLiveData()
    val articlesScreenState: LiveData<ArticleScreenState> = _articlesScreenState

    init {
        /**
         * Set to landing during initialization.
         */
        _articlesScreenState.postValue(ArticleScreenState.Landing)
        _articlesScreenState.addSource(repository.dataSource){
            when(it){
                is FetchArticlesStatus.Success -> _articlesScreenState.postValue(ArticleScreenState.ArticlesFetched(it.articles.articles))
                FetchArticlesStatus.Failure -> _articlesScreenState.postValue(ArticleScreenState.ArticlesFetchFailed)
            }
        }
    }

    fun fetchArticlesBySourceId(sourceId: String){
        /**
         * Set to loading as soon as the call is made.
         */
        _articlesScreenState.postValue(ArticleScreenState.Loading)
        viewModelScope.launch(dispatcherProvider.io) {
            repository.fetchArticlesBySourceId(sourceId)
        }
    }
}
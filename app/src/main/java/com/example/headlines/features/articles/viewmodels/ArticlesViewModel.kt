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
                /**
                 * We only need to know if the network call failed.
                 * Success is handled from the local data source as the local data source is the single version of the truth every single time.
                 */
                FetchArticlesStatus.Failure -> _articlesScreenState.postValue(ArticleScreenState.ArticlesFetchFailed)
            }
        }
    }

    private fun loadArticlesFromDbBySourceId(sourceId: String) {
        _articlesScreenState.addSource(repository.localSource(sourceId)) {
            when {
                /**
                 * Local source can only lead to success as we currently do not save the state of the api call in the database.
                 * However, that is something really interesting to explore and would love help from the team doing that.
                 */
                !it.isNullOrEmpty() -> {
                    _articlesScreenState.postValue(ArticleScreenState.ArticlesFetched(it))
                }
            }
        }
    }

    fun loadArticlesBySourceId(sourceId: String){
        /**
         *  Load from the local source first and then make the remote api call to update the local source.
         */
        loadArticlesFromDbBySourceId(sourceId)
        /**
         * Set to loading as soon as the call is made.
         */
        _articlesScreenState.postValue(ArticleScreenState.Loading)
        viewModelScope.launch(dispatcherProvider.io) {
            repository.fetchArticlesBySourceId(sourceId)
        }
    }
}
package com.example.headlines.features.articles.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.headlines.features.articles.destinations.ArticlesActivityDestination
import com.example.headlines.features.articles.models.Article
import com.example.headlines.utils.lifecycle.LiveEvent
import javax.inject.Inject

class ArticlesDestinationViewModel @Inject constructor(): ViewModel(){

    private val _destination: LiveEvent<ArticlesActivityDestination> =
        LiveEvent()
    val destinations: LiveData<ArticlesActivityDestination> = _destination

    fun startUp(sourceId: String) =
        _destination.postValue(ArticlesActivityDestination.StartUp(sourceId))

    fun invalidSourceId() = _destination.postValue(ArticlesActivityDestination.InvalidSource)

    fun navigateToDetails(article: Article) = _destination.postValue(ArticlesActivityDestination.Details(article))

    fun finish() = _destination.postValue(ArticlesActivityDestination.Finish)

}
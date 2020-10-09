package com.example.headlines.features.articles.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.headlines.features.articles.destinations.ArticlesActivityDestination
import com.example.headlines.features.articles.models.Article
import com.example.headlines.utils.lifecycle.LiveEvent
import javax.inject.Inject

/**
 * This view model is responsible for maintaining/tracking all of the destinations in the articles feature/workflow.
 */
class ArticlesDestinationViewModel @Inject constructor(): ViewModel(){

    private val _destination: LiveEvent<ArticlesActivityDestination> =
        LiveEvent()
    val destinations: LiveData<ArticlesActivityDestination> = _destination

    /**
     * Initializes the articles workflow by assigning a relevant [sourceId] to pull the articles based on it.
     */
    fun startUp(sourceId: String) =
        _destination.postValue(ArticlesActivityDestination.StartUp(sourceId))

    /**
     * In case we get invalid response from the api call OR the povided source id is null during the initialization process,
     * we should call this function to show user an appropriate error screen.
     */
    fun invalidSourceId() = _destination.postValue(ArticlesActivityDestination.InvalidSource)

    /**
     * As the name suggests, this navigates to the details screen.
     * [article] is required to populate the details screen.
     */
    fun navigateToDetails(article: Article) = _destination.postValue(ArticlesActivityDestination.Details(article))

    /**
     * Finishes the articles workflow by calling <code> finish() </code> on [com.example.headlines.features.articles.activities.ArticlesActivity]
     */
    fun finish() = _destination.postValue(ArticlesActivityDestination.Finish)

}
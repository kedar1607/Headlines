package com.example.headlines.features.sources.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.headlines.features.sources.destinations.SourcesActivityDestination
import com.example.headlines.utils.lifecycle.LiveEvent
import javax.inject.Inject

/**
 * This is a destination view model that is used to collaborate in between the fragments and the hosting activity in terms of where to navigate when specific criteria is met
 * or the action is performed.
 */
class SourcesDestinationViewModel @Inject constructor(): ViewModel() {
    private val _destination: LiveEvent<SourcesActivityDestination> =
        LiveEvent()
    val destinations: LiveData<SourcesActivityDestination> = _destination

    /**
     * As the name suggests navigates to the next articles screen which is not a part of [com.example.headlines.features.sources.activities.SourcesActivity].
     * In this case, we give the control to the activity and let the activity decide what to do.
     */
    fun navigateToArticles(articleID: String?){
        _destination.postValue(SourcesActivityDestination.Articles(articleID))
    }
}
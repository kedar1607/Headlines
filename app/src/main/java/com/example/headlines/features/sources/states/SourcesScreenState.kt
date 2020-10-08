package com.example.headlines.features.sources.states

import com.example.headlines.features.sources.models.Source

/**
 * This sealed class represents the every single state the sources screen can be.
 */
sealed class SourcesScreenState {
    /**
     * This state represents the very initial state of the sources screen. This state can simply be the loading state for the UI for now.
     */
    object Landing : SourcesScreenState()

    /**
     * This state represents that the network call to fetch the list of all the sources has been made. When the sources screen is in this state,
     * we can set the view state of the screen to loading as well.
     */
    object Loading: SourcesScreenState()

    /**
     * This state represents that the api call to load the sources has been successful and [sources] is the list of all the sources that need to be populated.
     */
    class SourcesFetched(val sources: List<Source>): SourcesScreenState()

    /**
     * This state represents that the fetch of the sources failed because of some reason. This object can essentially be a class if we need more information such as
     * status code etc.
     */
    object SourcesFetchFailed: SourcesScreenState()
}
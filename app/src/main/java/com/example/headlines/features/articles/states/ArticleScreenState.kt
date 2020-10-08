package com.example.headlines.features.articles.states

import com.example.headlines.features.articles.models.Article

/**
 * This sealed class represents the every single state the articles screen can be.
 */
sealed class ArticleScreenState {
    /**
     * This state represents the very initial state of the articles screen. This state can simply be the loading state for the UI for now.
     */
    object Landing : ArticleScreenState()

    /**
     * This state represents that the network call to fetch the list of all the articles has been made. When the articles screen is in this state,
     * we can set the view state of the screen to loading as well.
     */
    object Loading: ArticleScreenState()

    /**
     * This state represents that the api call to load the sources has been successful and [articles] is the list of all the articles that need to be populated.
     */
    class ArticlesFetched(val articles: List<Article>): ArticleScreenState()

    /**
     * This state represents that the fetch of the articles failed because of some reason. This object can essentially be a class if we need more information such as
     * status code etc.
     */
    object ArticlesFetchFailed: ArticleScreenState()
}
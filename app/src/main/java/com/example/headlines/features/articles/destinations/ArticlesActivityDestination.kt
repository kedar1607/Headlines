package com.example.headlines.features.articles.destinations

import com.example.headlines.features.articles.models.Article

/**
 * This is a destination view model that is used to collaborate in between the fragments and the hosting activity in terms of where to navigate when specific criteria is met
 * or the action is performed.
 */
sealed class ArticlesActivityDestination {
    /**
     * This destination is more or less considered as an initial destination (or rather a state)
     * (Usually destination means a specific screen/fragment in single-activity-multiple-fragments architecture but in this case destination term is used to define what state should
     * the fragment be in for lack of a better terminology. Note that [com.example.headlines.features.articles.states.ArticleScreenState] represents the state inferred by the status
     * of the network call from [com.example.headlines.features.articles.statuses.FetchArticlesStatus])
     */
    class StartUp(val sourceId: String): ArticlesActivityDestination()

    /**
     * When passed source ID is null, we can show a screen which shows error message and a button to navigate back to sources.
     * This can also be used of there's any additional error information from [com.example.headlines.features.articles.services.ArticlesService.getArticlesBySource] for a non-null
     * source ID.
     */
    object InvalidSource: ArticlesActivityDestination()

    /**
     * This destination is used to show the article details screen when user taps on any article in the list of articles on [StartUp] screen (Which is basically the home screen for
     * articles when a source is selected)
     */
    class Details(val article:Article): ArticlesActivityDestination()

    /**
     * This destination is used when we are navigating back to articles list from the article details view.
     */
    object BackToArticles: ArticlesActivityDestination()

    /**
     * This finishes the entire workflow (activity) [com.example.headlines.features.articles.activities.ArticlesActivity]
     * In this specific implementation, users will be navigated back to to sources screen [com.example.headlines.features.sources.activities.SourcesActivity].
     */
    object Finish: ArticlesActivityDestination()
}
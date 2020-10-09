package com.example.headlines.features.sources.destinations

/**
 * This class encompasses all of the destinations from the [com.example.headlines.features.sources.activities.SourcesActivity]
 * Note: Currently there's only one destination and that is [Articles].
 * We can add more destinations to this sealed class if there will be more than one destinations from [com.example.headlines.features.sources.activities.SourcesActivity].
 */
sealed class SourcesActivityDestination {
    /**
     * Destination to Articles screen from the sources screen.
     * [sourceId] is the id of the source required to populate all of the articles.
     * The reason [sourceId] is optional because we can show all articles if it's null (However that should not be the case in production if this app were to be published)
     */
    class Articles(val sourceId: String?): SourcesActivityDestination()
}
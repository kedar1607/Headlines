package com.example.headlines.network

import java.net.HttpURLConnection

/**
 * Base Result class that wraps API responses into successes or failures.
 */
sealed class APIResult<out T> {

    data class Success<T>(val data: T?) : APIResult<T>()
    data class Failure(val statusCode: Int, val rawResponse: String?) : APIResult<Nothing>() {

        companion object {

            /**
             * Helper function to group http error statuses that describe
             * a connection problem with the API
             */
            fun Int.isConnectionProblem(): Boolean {
                return when (this) {
                    HttpURLConnection.HTTP_FORBIDDEN, // When a url requires a VPN connection
                    HttpURLConnection.HTTP_CLIENT_TIMEOUT -> true
                    else -> false
                }
            }

        }

    }

    object NetworkError : APIResult<Nothing>() {
        override fun toString(): String {
            return "NetworkError"
        }
    }

}
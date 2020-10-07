package com.example.headlines.network

import com.example.headlines.BuildConfig
import okhttp3.*

/**
 * Pretty much every single api requires the apiKey to be added as a parameter. Instead of doing it every single time, we just use this [Interceptor]
 * to intercept the chain ([Interceptor.Chain]) and place the api key as the query parameter in the request.
 */
class APIKeyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val originalUrlWithoutApiKey = original.url

        val httpUrl =
            originalUrlWithoutApiKey.newBuilder().addQueryParameter("apiKey", BuildConfig.API_KEY)
                .build()

        val requestBuilder = original.newBuilder().url(httpUrl).build()
        return chain.proceed(requestBuilder)

    }
}
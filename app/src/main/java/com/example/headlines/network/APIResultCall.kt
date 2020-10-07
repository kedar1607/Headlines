package com.example.headlines.network

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

/**
 * [APIResult]s wrapper. Converts raw [Response]s into the [APIResult]s.
 */
class APIResultCall<T>(
    proxy: Call<T>
) : CallDelegate<T, APIResult<T>>(proxy) {

    override fun enqueueImpl(callback: Callback<APIResult<T>>) = proxy.enqueue(object :
        Callback<T> {

        override fun onResponse(call: Call<T>, response: Response<T>) {
            val code = response.code()
            val result = if (code in 200 until 300) {
                val body = response.body()
                APIResult.Success<T>(body)
            } else {
                APIResult.Failure(code, response.errorBody()?.string())
            }

            callback.onResponse(
                this@APIResultCall,
                Response.success(result)
            )
        }

        override fun onFailure(call: Call<T>, t: Throwable) {
            val result = if (t is IOException) {
                APIResult.NetworkError
            } else {
                APIResult.Failure(UNKNOWN, t.message)
            }

            callback.onResponse(
                this@APIResultCall,
                Response.success(result)
            )
        }

    })

    override fun cloneImpl() = APIResultCall(proxy.clone())

    companion object {

        const val UNKNOWN = -1

    }

}
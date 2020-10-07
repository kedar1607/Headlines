package com.example.headlines.network

import retrofit2.Call
import retrofit2.CallAdapter
import java.lang.reflect.Type

/**
 * Adapts API calls and returns [APIResult]s.
 */
class ApiResultAdapter(
    private val type: Type
) : CallAdapter<Type, Call<APIResult<Type>>> {

    override fun responseType() = type
    override fun adapt(call: Call<Type>): Call<APIResult<Type>> = APIResultCall(call)

}
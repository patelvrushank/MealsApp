package com.vrushank.mealsapp.data.retrofit

import okhttp3.Interceptor
import okhttp3.Response

class HeaderIntercepter : Interceptor {
    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
        val request = chain.request().newBuilder()
            .addHeader("Content-Type", "application/json")
            .addHeader("User-Agent","Mozilla/5.0")
            .build()
        return chain.proceed(request)
    }
}
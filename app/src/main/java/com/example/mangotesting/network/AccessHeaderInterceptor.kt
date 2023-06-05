package com.example.mangotesting.network

import okhttp3.Interceptor
import okhttp3.Response

object AccessHeaderInterceptor: Interceptor {

    private var token = ""

    fun setToken(token: String){
        this.token = token
    }

    override fun intercept(chain: Interceptor.Chain): Response = with(chain.request().newBuilder()){
        addHeader("Authorization", "Bearer $token").build()
        return chain.proceed(build())
    }

}
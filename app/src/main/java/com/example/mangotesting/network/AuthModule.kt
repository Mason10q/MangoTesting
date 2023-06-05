package com.example.mangotesting.network

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class AuthModule {

    @Provides
    fun provideGson(): Gson = GsonBuilder().setLenient().create()


    @Provides
    fun provideOkHttp() = OkHttpClient.Builder()
        .addInterceptor{chain ->
            val request = chain.request().newBuilder()
                .addHeader("accept", "application/json")
                .addHeader("Content-Type", "application/json")
                .build()

            return@addInterceptor chain.proceed(request)
        }
        .addInterceptor(AccessHeaderInterceptor)
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .build()


    @Provides
    fun provideApi(gson: Gson, client: OkHttpClient): AuthApi = Retrofit.Builder()
        .client(client)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .baseUrl("https://plannerok.ru/api/v1/users/")
        .build()
        .create(AuthApi::class.java)
}
package com.chand.learning.feedapp.api

import com.chand.learning.feedapp.data.FeedResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
//import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService  {

    @GET("/v2/{input}")
    suspend fun getFeeds(@Path("input") page:String):FeedResponse

//    @GET("everything?q=sports&apiKey=aa67d8d98c8e4ad1b4f16dbd5f3be348")
//    suspend fun getFeeds(
//        @Query("page") page: Int,
//        @Query("pageSize") pageSize: Int):FeedResponse

    companion object{
        private const val BASE_URL = "http://www.mocky.io" //"https://newsapi.org/v2/"

        fun create(): ApiService {
            val logger = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
            val client = OkHttpClient.Builder().addInterceptor(logger).build()

            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .client(client)
                .build()
                .create(ApiService::class.java)
        }
    }
}
package com.chand.learning.newsapp.api

import com.chand.learning.feedapp.data.FeedResponse
import com.chand.learning.feedapp.utility.FIRST_PAGE
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
//import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface ApiService  {

    @GET(FIRST_PAGE)
    suspend fun getFeeds(@QueryMap param:HashMap<String,String>):Response<FeedResponse>

    companion object{
        private const val BASE_URL = "http://www.mocky.io/v2/"

        fun create(): ApiService{
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
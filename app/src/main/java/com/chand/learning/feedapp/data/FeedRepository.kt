package com.chand.learning.feedapp.data

import android.util.Log
import com.chand.learning.feedapp.api.SafeApiRequest
import com.chand.learning.feedapp.utility.JsonLoader
import com.chand.learning.newsapp.api.ApiService
import java.io.IOException

class FeedRepository(private val api: ApiService, private val param: HashMap<String, String>) :
    SafeApiRequest() {

    suspend fun getFeeds(): FeedResponse {
        var response: FeedResponse
        try {
            response = apiRequest { api.getFeeds(param) }
//            response.let { db.getArticleDao().updateData(it) }
        } catch (e: IOException) {
            response = JsonLoader.feedResponse//db.getArticleDao().getArticles()
            Log.d(TAG, "Error->${e.message}")
        }
        return response
    }

    companion object {
        const val TAG = "NewsRepository"

        // For Singleton instantiation
        @Volatile
        private var instance: FeedRepository? = null

        fun getInstance(api: ApiService, param: HashMap<String, String>) =
            instance ?: synchronized(this) {
                instance ?: FeedRepository(api, param).also { instance = it }
            }
    }
}
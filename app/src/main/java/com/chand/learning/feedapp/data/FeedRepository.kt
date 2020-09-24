package com.chand.learning.feedapp.data

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.room.PrimaryKey
import com.chand.learning.feedapp.api.ApiService
import kotlinx.coroutines.flow.*

class FeedRepository(
    private val api: ApiService,
//    private val db:AppDataBase
) {

    fun getPostStream(): Flow<PagingData<Post>> {
         return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = 5),
            initialKey = 1,
            pagingSourceFactory = { FeedPagingSource(api) }
        ).flow
    }
}
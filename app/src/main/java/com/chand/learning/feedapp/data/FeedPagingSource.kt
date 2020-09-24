

package com.chand.learning.feedapp.data

import android.util.Log
import androidx.paging.PagingSource
import com.chand.learning.feedapp.utility.FIRST_PAGE
import com.chand.learning.feedapp.utility.SECOND_PAGE
import com.chand.learning.feedapp.api.ApiService
import com.chand.learning.feedapp.utility.THIRD_PAGE
import java.util.*

private const val PAGE_INDEX = 1

class FeedPagingSource(
    private val service: ApiService,
) : PagingSource<Int, Post>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Post> {
        val page = params.key ?: PAGE_INDEX
        Log.d(TAG, "load: $page")
        return try {
            val response = service.getFeeds(getRandom())
//            db.getArticleDao().updateData(response)
            val posts = response.posts
            LoadResult.Page(
                data = posts,
                null, nextKey = page + 1
            )
        } catch (exception: Exception) {
            Log.d(TAG, "loadException: $page")
            LoadResult.Error(exception)
        }
    }
    companion object{
        private const val TAG = "FeedPagingSource"
    }

    private fun getRandom():String{
        val list = listOf<String>(FIRST_PAGE, SECOND_PAGE, THIRD_PAGE)
        val random = Random()
        return list.get(random.nextInt(list.size))
    }
}

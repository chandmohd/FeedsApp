package com.chand.learning.feedapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.DataSource
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.chand.learning.feedapp.data.FeedRepository
import com.chand.learning.feedapp.data.Post
import kotlinx.coroutines.flow.*

class FeedViewModel(
    private val repo: FeedRepository
) : ViewModel() {
    var livePostData: Flow<PagingData<Post>>? = null

    fun getPost(): Flow<PagingData<Post>> {
       val result =  repo.getPostStream().cachedIn(viewModelScope)
        livePostData = result
        return result
    }

    fun getPostByLikes(): Flow<PagingData<Post>>? {
        val sortPost = livePostData
        return sortPost?.onEach {
            pagingData -> pagingData.filter { post -> post.likes ==post.likes }
        }
    }


}

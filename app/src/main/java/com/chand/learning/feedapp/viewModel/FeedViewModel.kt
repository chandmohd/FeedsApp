package com.chand.learning.feedapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.chand.learning.feedapp.data.FeedRepository
import com.chand.learning.feedapp.data.Post
import kotlinx.coroutines.flow.*

class FeedViewModel(
    private val repo: FeedRepository
) : ViewModel() {
    private var livePostData: Flow<PagingData<Post>>? = null

    fun getPost(): Flow<PagingData<Post>> {
       val result =  repo.getPostStream().cachedIn(viewModelScope)
        livePostData = result
        return result
    }
}
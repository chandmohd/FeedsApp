package com.chand.learning.feedapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chand.learning.feedapp.data.FeedRepository
import com.chand.learning.feedapp.data.Post
import com.chand.learning.feedapp.utility.Coroutines
import kotlinx.coroutines.Job
import retrofit2.http.POST

class FeedViewModel(private val repo:FeedRepository) :ViewModel() {
    val _data = MutableLiveData<List<Post>>()
    val post :LiveData<List<Post>>
    get() = _data

    private lateinit var  job:Job

    fun getFeeds(){
        job =  Coroutines.ioToMain({repo.getFeeds().posts},{_data.value = it})
    }

    override fun onCleared() {
        super.onCleared()
        if (::job.isInitialized)job.cancel()
    }
}
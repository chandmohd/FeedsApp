package com.chand.learning.feedapp.utility

import androidx.fragment.app.Fragment
import com.chand.learning.feedapp.data.FeedRepository
import com.chand.learning.feedapp.viewModel.FeedViewModelFactory
import com.chand.learning.newsapp.api.ApiService


object Injector {
     fun getFeedFactory(fragment: Fragment):FeedViewModelFactory {
        return FeedViewModelFactory(FeedRepository.getInstance(ApiService.create(), getParam()))
    }

    private fun getParam():HashMap<String,String>{
        val param = HashMap<String,String>()
        return param
    }
}
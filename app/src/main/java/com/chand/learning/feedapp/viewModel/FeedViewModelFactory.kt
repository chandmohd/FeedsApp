package com.chand.learning.feedapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.chand.learning.feedapp.data.FeedRepository
@Suppress("UNCHECKED_CAST")
class FeedViewModelFactory(private val respo:FeedRepository):ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return FeedViewModel(respo) as T
    }

}
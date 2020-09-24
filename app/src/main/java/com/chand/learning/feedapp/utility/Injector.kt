package com.chand.learning.feedapp.utility

import androidx.fragment.app.Fragment
import com.chand.learning.feedapp.data.FeedRepository
import com.chand.learning.feedapp.viewModel.FeedViewModelFactory
import com.chand.learning.feedapp.api.ApiService
import com.chand.learning.feedapp.data.AppDataBase
import javax.sql.DataSource


object Injector {
     fun getFeedViewModelFactory(fragment: Fragment):FeedViewModelFactory {
        return FeedViewModelFactory(FeedRepository(ApiService.create()))
    }

}
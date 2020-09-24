package com.chand.learning.feedapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.paging.PagingData
import com.chand.learning.feedapp.adapters.FeedsLoadStateAdapter
import com.chand.learning.feedapp.adapters.NewsAdapter
import com.chand.learning.feedapp.data.AppDataBase
import com.chand.learning.feedapp.data.Post
import com.chand.learning.feedapp.data.State
import com.chand.learning.feedapp.databinding.FragmentFeedsBinding
import com.chand.learning.feedapp.utility.FIRST_PAGE
import com.chand.learning.feedapp.utility.Injector
import com.chand.learning.feedapp.utility.SECOND_PAGE
import com.chand.learning.feedapp.viewModel.FeedViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FeedsFragment : Fragment() {
    private lateinit var binding: FragmentFeedsBinding
    private lateinit var adapter: NewsAdapter
    private var  postJob: Job?= null
    private val viewModel:FeedViewModel by viewModels { Injector.getFeedViewModelFactory(this) }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
         binding =  FragmentFeedsBinding.inflate(layoutInflater, container, false)
         context ?: return binding.root
        adapter = NewsAdapter()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        subscribeUi(adapter)
        initAdapter()

        binding.retryButton.setOnClickListener { adapter.retry() }
    }

    @ExperimentalCoroutinesApi
    private fun subscribeUi(adapter: NewsAdapter) {
        postJob?.cancel()
        postJob = lifecycleScope.launch {
            viewModel.getPost().collectLatest {
                adapter.submitData(it)
            }
        }
    }

    private fun initAdapter() {
        binding.rvFeeds.adapter = adapter.withLoadStateHeaderAndFooter(
            header = FeedsLoadStateAdapter{adapter.retry()},
            footer = FeedsLoadStateAdapter{adapter.retry()}
        )

        adapter.addLoadStateListener { loadState ->
            // Only show the list if refresh succeeds.
            binding.rvFeeds.isVisible = loadState.refresh is LoadState.NotLoading
            // Show loading spinner during initial load or refresh.
            binding.rvFeeds.isVisible = loadState.refresh is LoadState.Loading
            // Show the retry state if initial load or refresh fails.
            binding.retryButton.isVisible = loadState.refresh is LoadState.Error
            binding.progressBar.isVisible = !binding.retryButton.isVisible


            // Toast on any error, regardless of whether it came from RemoteMediator or PagingSource
            val errorState = loadState.source.append as? LoadState.Error
                ?: loadState.source.prepend as? LoadState.Error
                ?: loadState.append as? LoadState.Error
                ?: loadState.prepend as? LoadState.Error

            errorState?.let {
                Toast.makeText(context, "Wooops..Error", Toast.LENGTH_LONG).show()
            }
        }

    }

}
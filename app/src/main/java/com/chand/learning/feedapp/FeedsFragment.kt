package com.chand.learning.feedapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.PagingData
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
        binding.rvFeeds.adapter = adapter
        subscribeUi(adapter)
        return binding.root
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

//    private fun initState() {
//        binding.txtError.setOnClickListener { viewModel.retry() }
//        viewModel.getState().observe(this, Observer { state ->
//            binding.progressBar.visibility = if (viewModel.listIsEmpty() && state == State.LOADING) View.VISIBLE else View.GONE
//            binding.txtError.visibility = if (viewModel.listIsEmpty() && state == State.ERROR) View.VISIBLE else View.GONE
//            if (!viewModel.listIsEmpty()) {
//                adapter.setState(state ?: State.DONE)
//            }
//        })
//    }

}
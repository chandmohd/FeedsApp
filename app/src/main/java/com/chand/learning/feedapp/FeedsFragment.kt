package com.chand.learning.feedapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.chand.learning.feedapp.adapters.NewsAdapter
import com.chand.learning.feedapp.databinding.FragmentFeedsBinding
import com.chand.learning.feedapp.utility.Injector
import com.chand.learning.feedapp.viewModel.FeedViewModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FeedsFragment : Fragment() {
    private val viewModel:FeedViewModel by viewModels { Injector.getFeedFactory(this) }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val binding =  FragmentFeedsBinding.inflate(layoutInflater, container, false)
         context ?: return binding.root
        val adapter = NewsAdapter()
        binding.rvFeeds.adapter = adapter
        subscribeUi(adapter)
        return binding.root
    }

    private fun subscribeUi(adapter: NewsAdapter) {
        viewModel.getFeeds()
        viewModel.post.observe(viewLifecycleOwner) { news ->
            adapter.submitList(news)
        }
    }

}
package com.chand.learning.feedapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.chand.learning.feedapp.R
import com.chand.learning.feedapp.databinding.FragmentFeedDetailsBinding
import com.chand.learning.feedapp.utility.TRANSACTIONS
import com.google.android.material.transition.MaterialContainerTransform

/**
 * A simple [Fragment] subclass.
 * Use the [FeedDetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FeedDetailsFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = MaterialContainerTransform().setDuration(300)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val args :FeedDetailsFragmentArgs by navArgs()
        val binding =  FragmentFeedDetailsBinding.inflate(inflater, container, false)
        binding.item = args.post
        binding.root.transitionName = TRANSACTIONS
       return binding.root
    }
}
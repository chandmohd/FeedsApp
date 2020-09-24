

package com.chand.learning.feedapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.chand.learning.feedapp.R
import com.chand.learning.feedapp.databinding.ItemListFooterBinding

/**
 *  Adapter for header and footer on pagingation recyclerview in FeedsFragment
 *  Retry button will trigger to fetch data again
 */
class FeedsLoadStateAdapter(
        private val retry: () -> Unit
) : LoadStateAdapter<FeedsLoadStateAdapter.FeedLoadStateViewHolder>() {

    override fun onBindViewHolder(holder: FeedLoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): FeedLoadStateViewHolder {
        return FeedLoadStateViewHolder.create(
            parent,
            retry
        )
    }

    class FeedLoadStateViewHolder(
        private val binding: ItemListFooterBinding,
        retry: () -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.retryButton.setOnClickListener { retry.invoke() }
        }

        fun bind(loadState: LoadState) {
            if (loadState is LoadState.Error) {
                binding.errorMsg.text = loadState.error.localizedMessage
            }
            binding.progressBarFooter.isVisible = loadState is LoadState.Loading
            binding.retryButton.isVisible = loadState !is LoadState.Loading
            binding.errorMsg.isVisible = loadState !is LoadState.Loading
        }

        companion object {
            fun create(parent: ViewGroup, retry: () -> Unit): FeedLoadStateViewHolder {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_list_footer, parent, false)
                val binding = ItemListFooterBinding.bind(view)
                return FeedLoadStateViewHolder(
                    binding,
                    retry
                )
            }
        }
    }
}

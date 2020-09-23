

package com.chand.learning.feedapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.chand.learning.feedapp.data.Post
import com.chand.learning.feedapp.databinding.ItemFeedBinding


/**
 * Adapter for the [RecyclerView] in [FeedsFragmentList].
 */
class NewsAdapter : ListAdapter<Post, RecyclerView.ViewHolder>(NewsDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return NewsViewHolder(
            ItemFeedBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val plant = getItem(position)
        (holder as NewsViewHolder).bind(plant)
    }

    class NewsViewHolder(
        private val binding: ItemFeedBinding
    ) : RecyclerView.ViewHolder(binding.root) {
//        init {
//            binding.setClickListener {
//                binding.item?.let { news ->
//                    navigateToPlant(news, it)
//                }
//            }
//        }

//        private fun navigateToPlant(
//            article: Article,
//            view: View
//        ) {
//            val direction = NewsListFragmentDirections.actionNewsListFragmentToNewsDetailsFragment(article)
//            view.findNavController().navigate(direction)
//        }

        fun bind(item: Post) {
            binding.apply {
                this.item = item
                executePendingBindings()
            }
        }
    }
}

private class NewsDiffCallback : DiffUtil.ItemCallback<Post>() {

    override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem == newItem
    }

}
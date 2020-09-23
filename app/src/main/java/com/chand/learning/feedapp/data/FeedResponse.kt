package com.chand.learning.feedapp.data


import com.google.gson.annotations.SerializedName

data class FeedResponse(
    @SerializedName("page")
    val page: Int = 0,
    @SerializedName("posts")
    val posts: List<Post> = listOf()
)
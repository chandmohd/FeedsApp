package com.chand.learning.feedapp.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Post(
        @SerializedName("event_date")
        val eventDate: Int = 0,
        @SerializedName("event_name")
        val eventName: String = "",
        @SerializedName("id")
        val id: String = "",
        @SerializedName("likes")
        val likes: Int = 0,
        @SerializedName("shares")
        val shares: Int = 0,
        @SerializedName("thumbnail_image")
        val thumbnailImage: String = "",
        @SerializedName("views")
        val views: Int = 0
) : Parcelable
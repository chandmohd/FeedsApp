package com.chand.learning.newsapp.adapters

import android.text.format.DateFormat
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import java.sql.Date
import java.text.SimpleDateFormat
import java.util.*

@BindingAdapter("imageFromUrl")
fun bindImageFromUrl(view: ImageView, imageUrl: String?) {
    if (!imageUrl.isNullOrEmpty()) {
        Glide.with(view.context)
            .load(imageUrl)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(view)
    }
}

@BindingAdapter("isGone")
fun bindIsGone(view: TextView, isGone: String?) {
    view.visibility = if (isGone.isNullOrBlank()) {
        View.GONE
    } else {
        View.VISIBLE
    }
}

@BindingAdapter("dateFromTimeStamp")
fun dateFromTimeStamp(view:TextView,date:Int){
    val calendar = Calendar.getInstance(Locale.ENGLISH)
    calendar.timeInMillis = date * 1000L
    view.text = DateFormat.format("dd-MM-yyyy",calendar).toString()
}
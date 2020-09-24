package com.chand.learning.feedapp.data

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.Serializable
import java.util.*

class PostConverter:Serializable {
    private val gson = Gson()
    @TypeConverter
    fun stringToList(data: String?): List<Post> {
        if (data == null) {
            return Collections.emptyList()
        }

        val listType = object : TypeToken<List<Post>>() {

        }.type

        return gson.fromJson<List<Post>>(data, listType)
    }

    @TypeConverter
    fun listToString(someObjects: List<Post>): String {
        return gson.toJson(someObjects)
    }
}
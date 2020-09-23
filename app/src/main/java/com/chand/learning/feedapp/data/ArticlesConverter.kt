//package com.chand.learning.newsapp.data
//
//import androidx.room.TypeConverter
//import com.google.gson.Gson
//import com.google.gson.reflect.TypeToken
//import java.io.Serializable
//import java.util.*
//
//class ArticlesConverter:Serializable {
//    private val gson = Gson()
//    @TypeConverter
//    fun stringToList(data: String?): List<Article> {
//        if (data == null) {
//            return Collections.emptyList()
//        }
//
//        val listType = object : TypeToken<List<Article>>() {
//
//        }.type
//
//        return gson.fromJson<List<Article>>(data, listType)
//    }
//
//    @TypeConverter
//    fun listToString(someObjects: List<Article>): String {
//        return gson.toJson(someObjects)
//    }
//}
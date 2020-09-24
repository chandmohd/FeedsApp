//package com.chand.learning.feedapp.data
//
//import androidx.paging.DataSource
//import androidx.paging.PagingData
//import androidx.paging.PagingSource
//import androidx.room.*
//import kotlinx.coroutines.flow.Flow
//
//@Dao
//interface PostDao {
//    @Query("SELECT * FROM post_data")
//    fun getArticles(): PagingData<Post>
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun insertAll(article: FeedResponse)
//
//    @Update
//    fun updateData(article: FeedResponse)
//}
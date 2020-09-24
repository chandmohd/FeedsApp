package com.chand.learning.feedapp.data

import android.content.Context
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteDatabase
import com.chand.learning.feedapp.utility.DATABASE_NAME
import com.chand.learning.feedapp.utility.JsonLoader
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

abstract class AppDataBase : RoomDatabase() {

//    abstract fun getArticleDao(): PostDao

    companion object {

        // For Singleton instantiation
        @Volatile
        private var instance: AppDataBase? = null

        fun getInstance(context: Context): AppDataBase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context,
                AppDataBase::class.java,
                DATABASE_NAME
            ).addCallback(object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    CoroutineScope(Dispatchers.IO).launch {
//                        JsonLoader.feedResponse.let {
//                            getInstance(context).getArticleDao().insertAll(
//                                it
//                            )
//                        }
                    }
                }
            }
            ).build()

    }
}
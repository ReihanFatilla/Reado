package com.naufatio.BookApp.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.*
import com.naufatio.BookApp.data.BooksResponse

@Database(entities = [BooksResponse::class], version = 2)
abstract class BookDB : RoomDatabase() {
    abstract val bookDao: BookDao

    companion object{
        @Volatile
        var instace: BookDB? = null
        private var LOCK = Any()

        operator fun invoke(context: Context) = instace ?: synchronized(LOCK) {
            instace ?: buildDataBase(context).also {
                instace = it
            }
        }

        private fun buildDataBase(context: Context) =
            Room.databaseBuilder(context, BookDB::class.java, "bookmark.db")
                .fallbackToDestructiveMigration()
                .build()
    }
}
package com.naufatio.BookApp.data.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.naufatio.BookApp.data.BooksResponse

@Dao
interface BookDao {

    @Query("SELECT * FROM BooksResponse")
    fun getAllBookmark(): LiveData<List<BooksResponse>>

    @Insert
    fun addBookmark(book: BooksResponse)

    @Update
    fun updateBookmark(book: BooksResponse)

    @Delete
    fun deleteBookmark(book: BooksResponse)
}
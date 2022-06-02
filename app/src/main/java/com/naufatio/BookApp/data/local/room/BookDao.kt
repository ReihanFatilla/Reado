package com.naufatio.BookApp.data.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.naufatio.BookApp.data.BooksResponse

@Dao
interface BookDao {
    @Query("SELECT * FROM Book")
    fun getAllBookmark(): LiveData<List<Book>>

    @Insert
    fun addBookmark(book: Book)

    @Delete
    fun deleteBookmark(book: Book)
}
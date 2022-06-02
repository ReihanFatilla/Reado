package com.naufatio.BookApp.data.local

import android.content.Context
import androidx.lifecycle.LiveData
import com.naufatio.BookApp.data.BooksResponse
import com.naufatio.BookApp.data.ItemsItem
import com.naufatio.BookApp.data.local.room.Book
import com.naufatio.BookApp.data.local.room.BookDB
import com.naufatio.BookApp.data.local.sharedpreferences.BookPreference
import com.naufatio.BookApp.data.remote.ApiClient
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class BookRepository(context: Context) {
    private val preferences: BookPreference = BookPreference(context)
    private val dao = BookDB.invoke(context).bookDao
    private val apiService = ApiClient.getApiService()

    fun getRandomBook(responseHandler : (BooksResponse) -> Unit, errorHandler : (Throwable) -> Unit, books: String) {
        apiService.bookRandomCategory(books).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                responseHandler(it)
            }, {
                errorHandler(it)
            })
    }

    fun getBookById(responseHandler : (ItemsItem) -> Unit, errorHandler : (Throwable) -> Unit, id: String) {
        apiService.bookById(id).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                responseHandler(it)
            }, {
                errorHandler(it)
            })
    }

    fun getBookByCategory(responseHandler: (BooksResponse) -> Unit, errorHandler: (Throwable) -> Unit, category: String) {
        apiService.bookSearchByCategory("$category+subject:")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                responseHandler(it)
            }, {
                errorHandler(it)
            })
    }

    fun getBookByTitle(responseHandler: (BooksResponse) -> Unit, errorHandler: (Throwable) -> Unit, title: String) {
        apiService.bookBySearchWithSort("$title+intitle:")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                responseHandler(it)
            }, {
                errorHandler(it)
            })
    }


    fun getBookByAuthor(responseHandler: (BooksResponse) -> Unit, errorHandler: (Throwable) -> Unit, author: String) {
        apiService.bookBySearchWithSort("$author+inauthor:")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                responseHandler(it)
            }, {
                errorHandler(it)
            })
    }

    fun getBookByPublisher(responseHandler: (BooksResponse) -> Unit, errorHandler: (Throwable) -> Unit, author: String) {
        apiService.bookBySearchWithSort("$author+inpublisher:")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                responseHandler(it)
            }, {
                errorHandler(it)
            })
    }

    fun getPrefString(key: String): String? {
        return preferences.getString(key)
    }

    fun putPrefString(key: String, value: String) {
        preferences.put(key, value)
    }

    fun putPrefInt(key: String, value: Int) {
        preferences.put(key, value)
    }

    fun addBookmark(book: Book) {
        dao.addBookmark(book)
    }

    fun getBookmark(): LiveData<List<Book>> {
        return dao.getAllBookmark()
    }

    fun deleteBookmark(book: Book) {
        dao.deleteBookmark(book)
    }



}
package com.naufatio.BookApp.presentation.home

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.naufatio.BookApp.data.BooksResponse
import com.naufatio.BookApp.data.local.BookRepository
import com.naufatio.BookApp.data.local.sharedpreferences.BookPreference
import com.naufatio.BookApp.data.remote.ApiClient
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class HomeViewModel(application: Application):AndroidViewModel(application) {

    private var repository: BookRepository = BookRepository(application)

    var booksResponse = MutableLiveData<BooksResponse>()


    init {
        repository = BookRepository(application)
    }

    fun getUserName():String? {
        return repository.getPrefString(BookPreference.PREF_USER)
    }

    fun setUserName(name: String){
        repository.putPrefString(BookPreference.PREF_USER, name)
    }


    fun getRandomBook(responseHandler : (BooksResponse) -> Unit, errorHandler : (Throwable) -> Unit, books: String) {

        ApiClient.getApiService().bookBySearch(books).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                responseHandler(it)
            }, {
                errorHandler(it)
            })
    }

    fun getBookByCategory(responseHandler: (BooksResponse) -> Unit, errorHandler: (Throwable) -> Unit, category: String) {
        ApiClient.getApiService().bookSearchByCategory(category+"+insubject:")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                responseHandler(it)
            }, {
                errorHandler(it)
            })
    }

    fun getBookByTitle(responseHandler: (BooksResponse) -> Unit, errorHandler: (Throwable) -> Unit, title: String) {
        ApiClient.getApiService().bookSearchByCategory(title+"+intitle:")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                responseHandler(it)
            }, {
                errorHandler(it)
            })
    }


    fun getBookByAuthor(responseHandler: (BooksResponse) -> Unit, errorHandler: (Throwable) -> Unit, author: String) {
        ApiClient.getApiService().bookSearchByCategory(author+"+inauthor:")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                responseHandler(it)
            }, {
                errorHandler(it)
            })
    }

    fun getRandomBooks(books: String) {
        getRandomBook({
            booksResponse.value = it
            Log.i("MainActivity", "getRandomBooks: $it")
        }, {
            Log.e("MainActivity", "getRandomBooks: $it", )
        }, books)
    }

}
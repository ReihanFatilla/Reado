package com.naufatio.BookApp.presentation.home

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.naufatio.BookApp.data.BooksResponse
import com.naufatio.BookApp.data.ItemsItem
import com.naufatio.BookApp.data.local.BookRepository
import com.naufatio.BookApp.data.local.sharedpreferences.BookPreference
import com.naufatio.BookApp.data.remote.ApiClient
import com.naufatio.BookApp.helper.constant
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class HomeViewModel(application: Application):AndroidViewModel(application) {

    private var repository: BookRepository = BookRepository(application)

    var booksResponse = MutableLiveData<BooksResponse>()
    var recentBooksResponse = MutableLiveData<BooksResponse>()


    fun getUserName():String? {
        return repository.getPrefString(BookPreference.PREF_USER)
    }

    fun getRecentBookId():String? {
        return repository.getPrefString(constant.PREF_RECENT_BOOK_ID)
    }

    fun setUserName(name: String){
        repository.putPrefString(BookPreference.PREF_USER, name)
    }


    private fun getRandomBook(responseHandler : (BooksResponse) -> Unit, errorHandler : (Throwable) -> Unit, books: String) {
        ApiClient.getApiService().bookRandomCategory(books).subscribeOn(Schedulers.io())
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

    private fun getBookByCategory(responseHandler: (BooksResponse) -> Unit, errorHandler: (Throwable) -> Unit, category: String) {
        ApiClient.getApiService().bookSearchByCategory(category+"+insubject:")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                responseHandler(it)
            }, {
                errorHandler(it)
            })
    }

    private fun getBookById(responseHandler : (BooksResponse) -> Unit, errorHandler : (Throwable) -> Unit, id: String) {
        ApiClient.getApiService().bookById(id).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                responseHandler(it)
            }, {
                errorHandler(it)
            })
    }

    fun getBooksById(id: String) {
        getBookById({
            recentBooksResponse.value = it
            Log.i("recentBooks", "getRandomBooks: $it")
        }, {
            Log.e("MainActivity", "getRandomBooks: $it", )
        }, id)
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



    fun getRandomBooksByCategory(category: String) {
        getBookByCategory({
            booksResponse.value = it
            Log.i("HomeViewModel", "getRandomBooks: $it")
        }, {
            Log.e("HomeViewModel", "getRandomBooks: $it", )
        }, category)
    }

}
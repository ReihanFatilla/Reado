package com.naufatio.BookApp.presentation.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.naufatio.BookApp.data.BooksResponse
import com.naufatio.BookApp.data.local.BookRepository
import com.naufatio.BookApp.data.local.sharedpreferences.BookPreference
import com.naufatio.BookApp.data.remote.ApiClient
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class HomeViewModel(application: Application):AndroidViewModel(application) {
    private lateinit var repository: BookRepository
    init {
        repository = BookRepository(application)
    }

    fun getUserName():String? {
        return repository.getPrefString(BookPreference.PREF_USER)
    }

    fun setUserName(name: String){
        repository.putPrefString(BookPreference.PREF_USER, name)
    }

    fun getData(responseHandler : (List<BooksResponse>) -> Unit, errorHandler : (Throwable) -> Unit, books: String) {
        ApiClient.getApiService().bookBySearch(books).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                responseHandler(it)
            }, {
                errorHandler(it)
            })
    }

    fun getBookByCategory(responseHandler: (List<BooksResponse>) -> Unit, errorHandler: (Throwable) -> Unit, category: String) {
        ApiClient.getApiService().bookSearchByCategory(category+"+insubject:")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                responseHandler(it)
            }, {
                errorHandler(it)
            })
    }

    fun getBookByTitle(responseHandler: (List<BooksResponse>) -> Unit, errorHandler: (Throwable) -> Unit, title: String) {
        ApiClient.getApiService().bookSearchByCategory(title+"+intitle:")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                responseHandler(it)
            }, {
                errorHandler(it)
            })
    }


}
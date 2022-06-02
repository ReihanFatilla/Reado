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
import java.lang.invoke.MethodHandles
import java.lang.invoke.MethodHandles.constant

class HomeViewModel(application: Application):AndroidViewModel(application) {

    private var repository: BookRepository = BookRepository(application)

    var booksResponse = MutableLiveData<BooksResponse>()
    var recentBooksResponse = MutableLiveData<ItemsItem>()

    fun getUserName():String? {
        return repository.getPrefString(constant.PREF_USER)
    }

    fun getRecentBookId():String? {
        return repository.getPrefString(constant.PREF_RECENT_BOOK_ID)
    }

    fun setUserName(name: String){
        repository.putPrefString(constant.PREF_USER, name)
    }

    fun getRandomBooks(books: String) {
        repository.getRandomBook({
            booksResponse.value = it
        }, {}, books)
    }

    fun getBooksById(id: String) {
        repository.getBookById({
            recentBooksResponse.value = it
        }, {}, id)
    }

    fun getRandomBooksByCategory(category: String) {
        repository.getBookByCategory({
            booksResponse.value = it
        }, {}, category)
    }

}
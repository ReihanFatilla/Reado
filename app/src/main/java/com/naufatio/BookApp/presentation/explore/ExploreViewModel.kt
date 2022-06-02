package com.naufatio.BookApp.presentation.explore

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.naufatio.BookApp.data.BooksResponse
import com.naufatio.BookApp.data.local.BookRepository

class ExploreViewModel(application: Application) : AndroidViewModel(application) {
    private var repository: BookRepository = BookRepository(application)

    var booksResponse = MutableLiveData<BooksResponse>()

    fun searchBookByQuery(query: String) {
        repository.searchBookQuery({
            booksResponse.value = it
        }, {}, query)
    }

    fun searchBookInTitle(title: String) {
        repository.getBookByTitle({
            booksResponse.value = it
        }, {}, title)
    }

    fun searchBookInAuthor(author: String) {
        repository.getBookByAuthor({
            booksResponse.value = it
        }, {}, author)
    }

    fun searchBookInCategory(category: String) {
        repository.getBookByCategory({
            booksResponse.value = it
        }, {}, category)
    }

    fun searchBookInPublisher(publisher: String) {
        repository.getBookByPublisher({
            booksResponse.value = it
        }, {}, publisher)
    }



}
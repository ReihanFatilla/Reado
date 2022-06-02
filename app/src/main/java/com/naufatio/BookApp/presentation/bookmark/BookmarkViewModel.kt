package com.naufatio.BookApp.presentation.bookmark

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.naufatio.BookApp.data.local.BookRepository

class BookmarkViewModel(application: Application): AndroidViewModel(application) {
    var repository: BookRepository = BookRepository(application)

    fun getBookmark() = repository.getBookmark()
}
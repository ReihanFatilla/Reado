package com.naufatio.BookApp.presentation.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.naufatio.BookApp.data.local.BookRepository
import com.naufatio.BookApp.helper.constant

class DetailViewModel(application: Application): AndroidViewModel(application) {
    var repository: BookRepository = BookRepository(application)

    fun saveRecentBookId(id: String){
        repository.putPrefString(constant.PREF_RECENT_BOOK_ID, id)
    }
}
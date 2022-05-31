package com.naufatio.BookApp.presentation.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.naufatio.BookApp.data.local.BookRepository
import com.naufatio.BookApp.data.local.sharedpreferences.BookPreference

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

}
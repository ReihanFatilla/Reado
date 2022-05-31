package com.naufatio.BookApp.data.local

import android.content.Context
import com.naufatio.BookApp.data.local.sharedpreferences.BookPreference

class BookRepository(context: Context) {
    lateinit var preferences: BookPreference

    init {
        preferences = BookPreference(context)
    }

    fun getPrefString(key: String): String? {
        return preferences.getString(key)
    }

    fun putPrefString(key: String, value: String) {
        preferences.put(key, value)
    }

}
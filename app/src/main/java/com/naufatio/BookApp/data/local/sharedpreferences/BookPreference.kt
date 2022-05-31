package com.naufatio.BookApp.data.local.sharedpreferences

import android.content.Context
import android.content.SharedPreferences

class BookPreference(context: Context) {
    var sharedPreferences: SharedPreferences
    var prefEditor: SharedPreferences.Editor

    init {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        prefEditor = sharedPreferences.edit()
    }

    fun put(key: String, value: Boolean){
        prefEditor.putBoolean(key, value)
            .apply()
    }

    fun put(key: String, value: String){
        prefEditor.putString(key, value)
            .apply()
    }

    fun getBoolean(key: String): Boolean {
        return sharedPreferences.getBoolean(key, false)
    }

    fun getString(key: String): String? {
        return sharedPreferences.getString(key, null)
    }

    fun clear(){
        prefEditor.clear()
            .apply()
    }

    companion object{
        const val PREF_RECENT_BOOK = "recent_book_preference"
        const val PREF_BOOK_TITLE = "PREF_BOOK_TITLE"
//        const val PREF_BOOK_TITLE = "PREF_BOOK_TITLE"
//        const val PREF_BOOK_TITLE = "PREF_BOOK_TITLE"


        const val PREF_USER = "user_preference"
        const val PREF_LOGIN_STATUS = "PREF_IS_LOGIN"
        const val PREF_NAME = "PREF_NAME"
    }
}
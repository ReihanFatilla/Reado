package com.naufatio.BookApp.data.local.sharedpreferences

import android.content.Context
import android.content.SharedPreferences
import com.naufatio.BookApp.helper.constant

class BookPreference(context: Context) {
    var sharedPreferences: SharedPreferences
    var prefEditor: SharedPreferences.Editor

    init {
        sharedPreferences = context.getSharedPreferences(constant.PREF_NAME, Context.MODE_PRIVATE)
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

    fun put(key: String, value: Int){
        prefEditor.putInt(key, value)
            .apply()
    }

    fun getBoolean(key: String): Boolean {
        return sharedPreferences.getBoolean(key, false)
    }

    fun getInteger(key: String): Int {
        return sharedPreferences.getInt(key, 1)
    }

    fun getString(key: String): String? {
        return sharedPreferences.getString(key, null)
    }

    fun clear(){
        prefEditor.clear()
            .apply()
    }
}
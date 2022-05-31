package com.naufatio.BookApp.helper

import com.naufatio.BookApp.data.BooksResponse

interface OnItemClickCallback {
    fun onItemClicked(data: BooksResponse)
}
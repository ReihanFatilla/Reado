package com.naufatio.BookApp.helper

import com.naufatio.BookApp.data.BooksResponse
import com.naufatio.BookApp.data.ItemsItem

interface OnItemClickCallback {
    fun onItemClicked(data: ItemsItem)
}
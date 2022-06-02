package com.naufatio.BookApp.helper

import androidx.recyclerview.widget.DiffUtil
import com.naufatio.BookApp.data.local.room.Book

class BookDiffUtil(private val oldList: List<Book>, private val newList: List<Book>)
    : DiffUtil.Callback(){
    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val OldList = oldList[oldItemPosition]
        val NewList = newList[newItemPosition]
        return OldList.id == NewList.id
                && OldList.title == NewList.title
                && OldList.author == NewList.author
                && OldList.description == NewList.description
                && OldList.imageUrl == NewList.imageUrl
                && OldList.rating == NewList.rating
    }
}

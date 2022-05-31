package com.naufatio.BookApp.data.local.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Book(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val author: String,
    val description: String,
    val imageUrl: String,
    val rating: Float,
    val category: String
)

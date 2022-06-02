package com.naufatio.BookApp.data.local.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Book(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val title: String,
    val author: String,
    val description: String,
    val imageUrl: String,
    val rating: String,
)

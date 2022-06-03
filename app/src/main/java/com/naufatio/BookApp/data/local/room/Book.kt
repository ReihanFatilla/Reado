package com.naufatio.BookApp.data.local.room

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class Book(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val title: String,
    val author: String,
    val description: String,
    val imageUrl: String,
    val rating: String,
): Parcelable

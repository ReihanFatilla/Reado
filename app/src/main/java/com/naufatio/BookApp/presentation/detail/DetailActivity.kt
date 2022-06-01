package com.naufatio.BookApp.presentation.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.naufatio.BookApp.R
import com.naufatio.BookApp.data.BooksResponse

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        intent.getParcelableExtra<BooksResponse>("")
    }
}
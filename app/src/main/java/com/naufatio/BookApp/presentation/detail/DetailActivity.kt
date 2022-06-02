package com.naufatio.BookApp.presentation.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.naufatio.BookApp.R
import com.naufatio.BookApp.data.BooksResponse
import com.naufatio.BookApp.data.ItemsItem
import com.naufatio.BookApp.helper.constant
import com.naufatio.BookApp.presentation.home.HomeViewModel

class DetailActivity : AppCompatActivity() {

    private var _viewModel: DetailViewModel? = null
    private val viewModel get() = _viewModel as DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        _viewModel = ViewModelProvider(this)[DetailViewModel::class.java]

        val intentData = intent.getParcelableExtra<ItemsItem>(constant.EXTRA_BOOK_INTENT)

        val bookId = intentData?.id
        bookId?.let { saveRecentViewedBook(it) }
    }

    private fun saveRecentViewedBook(id: String) {
        viewModel.saveRecentBookId(id)
    }
}
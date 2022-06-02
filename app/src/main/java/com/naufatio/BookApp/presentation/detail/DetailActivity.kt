package com.naufatio.BookApp.presentation.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.naufatio.BookApp.R
import com.naufatio.BookApp.data.BooksResponse
import com.naufatio.BookApp.data.ItemsItem
import com.naufatio.BookApp.databinding.ActivityDetailBinding
import com.naufatio.BookApp.helper.HelperFunction
import com.naufatio.BookApp.helper.constant
import com.naufatio.BookApp.presentation.home.HomeViewModel

class DetailActivity : AppCompatActivity() {

    private var _binding: ActivityDetailBinding? = null
    private val binding get() = _binding as ActivityDetailBinding

    private var _viewModel: DetailViewModel? = null
    private val viewModel get() = _viewModel as DetailViewModel

    private var intentData: ItemsItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        _viewModel = ViewModelProvider(this)[DetailViewModel::class.java]
        intentData = intent.getParcelableExtra(constant.EXTRA_BOOK_INTENT)

        setUpDetail()

        val bookId = intentData?.id
        bookId?.let { saveRecentViewedBook(it) }
    }

    private fun setUpDetail() {
        var authors = ""
        var image: String? = ""
        var rating = (intentData?.volumeInfo?.averageRating ?: (1..9).random()).toString() + "." + (intentData?.volumeInfo?.averageRating ?: (1..9).random()).toString()
        if (intentData?.volumeInfo?.authors != null) {
            authors = intentData!!.volumeInfo?.authors?.joinToString(", ") ?: ""
        } else {
            authors = "-"
        }
        if (intentData?.volumeInfo?.imageLinks?.large != null) {
            image = intentData?.volumeInfo!!.imageLinks?.large
        } else {
            image = intentData?.volumeInfo?.imageLinks?.thumbnail
        }

        binding.apply {
            tvBookTitle.text = intentData?.volumeInfo?.title
            tvDescBook.text = intentData?.volumeInfo?.description
            tvBookAuthor.text = authors
            tvBookRating.text = rating
            tvRatingMaturerity.text = intentData?.volumeInfo?.maturityRating

            Glide.with(this@DetailActivity)
                .load(image)
                .apply(RequestOptions())
                .override(500, 500)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH)
                .into(imgBookDetail)

            cvBookCover.setCardBackgroundColor(HelperFunction.generateRandomColor())

            btnBack.setOnClickListener {
                finish()
            }
        }

    }

    private fun saveRecentViewedBook(id: String) {
        viewModel.saveRecentBookId(id)
    }
}
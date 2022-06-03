package com.naufatio.BookApp.presentation.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.naufatio.BookApp.data.ItemsItem
import com.naufatio.BookApp.data.local.room.Book
import com.naufatio.BookApp.databinding.ActivityDetailBinding
import com.naufatio.BookApp.helper.HelperFunction
import com.naufatio.BookApp.helper.constant

class DetailActivity : AppCompatActivity() {

    private var _binding: ActivityDetailBinding? = null
    private val binding get() = _binding as ActivityDetailBinding

    private var _viewModel: DetailViewModel? = null
    private val viewModel get() = _viewModel as DetailViewModel
    lateinit var rating: String

    private var intentData: ItemsItem? = null
    private var intentBookmark: Book? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        _viewModel = ViewModelProvider(this)[DetailViewModel::class.java]
        if(intent.getStringExtra(constant.EXTRA_ORIGIN) == constant.EXTRA_ORIGIN_BOOKMARK){
            intentBookmark = intent.getParcelableExtra(constant.EXTRA_BOOKMARK_INTENT)
        } else {
            intentData = intent.getParcelableExtra(constant.EXTRA_BOOK_INTENT)
        }

        setUpDetail()
        setUpFavoriteFeature()

        val bookId = intentData?.id
        bookId?.let { saveRecentViewedBook(it) }
    }

    private fun setUpFavoriteFeature() {
        binding.apply {
            btnFavorite.setOnClickListener {
                if (btnFavorite.isChecked) {
                    var bookmarkBook = Book(
                        intentData?.id!!,
                        intentData?.volumeInfo?.title!!,
                        intentData?.volumeInfo?.authors?.get(0)!!,
                        intentData?.volumeInfo?.description!!,
                        intentData?.volumeInfo?.imageLinks?.thumbnail!!,
                        rating,
                        )
                    viewModel.addBookmark(bookmarkBook)
                    Toast.makeText(applicationContext, "${intentData?.volumeInfo?.title} added to bookmark", Toast.LENGTH_SHORT).show()
                } else {

                }
            }
        }
    }

    private fun setUpDetail() {
        var authors = ""
        var image: String? = ""
        var desc: String? = ""
        var title: String? = ""
        if (intentData != null){
            rating = (intentData?.volumeInfo?.averageRating
                ?: (1..9).random()).toString() + "." + (intentData?.volumeInfo?.averageRating
                ?: (1..9).random()).toString()
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
            desc = intentData?.volumeInfo?.description
            title = intentData?.volumeInfo?.title
        } else {
            rating = intentBookmark?.rating ?: "0.0"
            if (intentBookmark?.author != null) {
                authors = intentBookmark!!.author
            } else {
                authors = "-"
            }
            image = intentBookmark?.imageUrl
            desc = intentBookmark?.description
            title = intentBookmark?.title
        }


        binding.apply {
            tvBookTitle.text = title
            tvDescBook.text = desc
            tvBookAuthor.text = authors
            tvBookRating.text = rating

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
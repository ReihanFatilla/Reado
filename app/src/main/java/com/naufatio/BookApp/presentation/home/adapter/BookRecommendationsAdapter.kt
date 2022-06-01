package com.naufatio.BookApp.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.naufatio.BookApp.data.BooksResponse
import com.naufatio.BookApp.data.ItemsItem
import com.naufatio.BookApp.databinding.RowItemHomeRecommendationBinding
import com.naufatio.BookApp.helper.OnItemClickCallback

class BookRecommendationsAdapter : RecyclerView.Adapter<BookRecommendationsAdapter.MyViewHolder>() {

    private var listBooksRecommendation = ArrayList<ItemsItem>()

    private var onItemClickCallBack: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallBack = onItemClickCallback
    }

    fun setData(data: List<ItemsItem>?) {
        if (data == null) return
        listBooksRecommendation.clear()
        listBooksRecommendation.addAll(data)
    }

    class MyViewHolder(val binding: RowItemHomeRecommendationBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MyViewHolder(
        RowItemHomeRecommendationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = listBooksRecommendation[position]
        holder.binding.apply {
            tvBookTitle.text = data.volumeInfo?.title

            var authors = ""
            if (data.volumeInfo?.authors != null) {
                authors = data.volumeInfo.authors.joinToString(", ")
            } else {
                authors = "-"
            }

            var image: String? = ""

            if (data.volumeInfo?.imageLinks?.large != null) {
                image = data.volumeInfo.imageLinks.large
            } else {
                image = data.volumeInfo?.imageLinks?.thumbnail
            }

            tvAuthorBook.text = authors
            tvRatingBook.text = (data.volumeInfo?.averageRating ?: 0).toString()
            Glide.with(imgBook.context)
                .load(image)
                .apply(RequestOptions())
                .override(500, 500)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH)
                .into(imgBook)
        }

        holder.itemView.setOnClickListener {
            onItemClickCallBack?.onItemClicked(data)
        }
    }

    override fun getItemCount() = listBooksRecommendation.size
}
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
import com.naufatio.BookApp.databinding.RowItemHomeTabBarBinding
import com.naufatio.BookApp.helper.OnItemClickCallback

class BookTabbarAdapter:RecyclerView.Adapter<BookTabbarAdapter.MyViewHolder>() {

    private var listBooksTabbar = ArrayList<ItemsItem>()

    private var onItemClickCallBack: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallBack = onItemClickCallback
    }

    fun setData(data: List<ItemsItem>?) {
        if (data == null) return
        listBooksTabbar.clear()
        listBooksTabbar.addAll(data)
    }

    class MyViewHolder(val binding: RowItemHomeTabBarBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MyViewHolder(
        RowItemHomeTabBarBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = listBooksTabbar[position]

        var authors = ""
        var image: String? = ""
        var rating = (data.volumeInfo?.averageRating ?: (1..9).random()).toString() + "." + (data.volumeInfo?.averageRating ?: (1..9).random()).toString()

        if (data.volumeInfo?.authors != null) {
            authors = data.volumeInfo.authors.joinToString(", ")
        } else {
            authors = "-"
        }
        if (data.volumeInfo?.imageLinks?.large != null) {
            image = data.volumeInfo.imageLinks.large
        } else {
            image = data.volumeInfo?.imageLinks?.thumbnail
        }

        holder.binding.apply {
            tvTitleBook.text = data.volumeInfo?.title
            tvAuthorBook.text = authors
            tvRatingBook.text = rating
            tvDescBook.text = data.volumeInfo?.description
            Glide.with(imgBook.context)
                .load(image)
                .apply(RequestOptions())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH)
                .into(imgBook)
        }

        holder.itemView.setOnClickListener {
            onItemClickCallBack?.onItemClicked(data)
        }
    }

    override fun getItemCount(): Int = listBooksTabbar.size
}
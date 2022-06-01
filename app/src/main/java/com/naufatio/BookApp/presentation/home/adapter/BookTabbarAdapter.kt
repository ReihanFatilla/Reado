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
        holder.binding.apply {
            tvTitleBook.text = data.volumeInfo?.title
            tvAuthorBook.text = data.volumeInfo?.authors.toString()
            tvRatingBook.text = (data.volumeInfo?.averageRating ?: 0).toString()
            Glide.with(imgBook.context)
                .load(data.volumeInfo?.imageLinks?.thumbnail)
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

    override fun getItemCount(): Int = listBooksTabbar.size
}
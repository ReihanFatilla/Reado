package com.naufatio.BookApp.presentation.bookmark.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.naufatio.BookApp.data.local.room.Book
import com.naufatio.BookApp.databinding.RowItemHomeTabBarBinding
import com.naufatio.BookApp.helper.BookDiffUtil
import com.naufatio.BookApp.helper.constant
import com.naufatio.BookApp.notification.NotificationService
import com.naufatio.BookApp.presentation.detail.DetailActivity

class BookmarkAdapter:RecyclerView.Adapter<BookmarkAdapter.MyViewHolder>() {

    private var listBooksTabbar = ArrayList<Book>()

    fun setData(newList: List<Book>) {
        val bookDiffUtil = BookDiffUtil(listBooksTabbar, newList)
        val diffUtilResult = DiffUtil.calculateDiff(bookDiffUtil)
        listBooksTabbar.clear()
        listBooksTabbar.addAll(newList)
        diffUtilResult.dispatchUpdatesTo(this)
    }

    class MyViewHolder(val binding: RowItemHomeTabBarBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MyViewHolder(
        RowItemHomeTabBarBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = listBooksTabbar[position]

        holder.binding.apply {
            tvTitleBook.text = data.title
            tvAuthorBook.text = data.author
            tvRatingBook.text = data.rating
            tvDescBook.text = data.description
            Glide.with(imgBook.context)
                .load(data.imageUrl)
                .apply(RequestOptions())
                .override(500, 500)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH)
                .into(imgBook)
        }

        holder.itemView.setOnClickListener {
            holder.binding.tvDescBook.context.startActivity(
                Intent(holder.binding.tvDescBook.context, DetailActivity::class.java)
                    .putExtra(constant.EXTRA_BOOKMARK_INTENT, data)
                    .putExtra(constant.EXTRA_ORIGIN, constant.EXTRA_ORIGIN_BOOKMARK)
            )
        }
    }

    override fun getItemCount(): Int = listBooksTabbar.size
}
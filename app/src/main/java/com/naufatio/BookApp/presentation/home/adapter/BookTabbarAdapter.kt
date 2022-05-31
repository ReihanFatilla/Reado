package com.naufatio.BookApp.presentation.home.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = listBooksTabbar[position]
        holder.binding.apply {
            tvTitleBook.text;
            tvAuthorBook.text;
            tvRatingBook.text;
        }

        holder.itemView.setOnClickListener {
            onItemClickCallBack?.onItemClicked(data)
        }
    }

    override fun getItemCount(): Int = listBooksTabbar.size
}
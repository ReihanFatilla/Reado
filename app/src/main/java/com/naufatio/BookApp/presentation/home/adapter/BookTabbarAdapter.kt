package com.naufatio.BookApp.presentation.home.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.naufatio.BookApp.databinding.RowItemHomeTabBarBinding

class BookTabbarAdapter:RecyclerView.Adapter<BookTabbarAdapter.MyViewHolder>() {

    private var listBooksTabbar = ArrayList<String>()

    fun setData(data: List<String>?) {
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
    }

    override fun getItemCount(): Int = listBooksTabbar.size
}
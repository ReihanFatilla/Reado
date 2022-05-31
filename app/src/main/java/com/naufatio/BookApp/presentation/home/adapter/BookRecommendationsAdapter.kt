package com.naufatio.BookApp.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.naufatio.BookApp.data.BooksResponse
import com.naufatio.BookApp.databinding.RowItemHomeRecommendationBinding
import com.naufatio.BookApp.helper.OnItemClickCallback

class BookRecommendationsAdapter : RecyclerView.Adapter<BookRecommendationsAdapter.MyViewHolder>() {

    private var listBooksRecommendation = ArrayList<BooksResponse>()

    private var onItemClickCallBack: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallBack = onItemClickCallback
    }

    fun setData(data: List<BooksResponse>?) {
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
            tvBookTitle.text;
            tvAuthorBook.text;
            tvRatingBook.text;
        }

        holder.itemView.setOnClickListener {
            onItemClickCallBack?.onItemClicked(data)
        }
    }

    override fun getItemCount() = listBooksRecommendation.size
}
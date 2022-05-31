package com.naufatio.BookApp.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.naufatio.BookApp.databinding.RowItemHomeRecommendationBinding

class BookRecommendationsAdapter : RecyclerView.Adapter<BookRecommendationsAdapter.MyViewHolder>() {

    private var listBooksRecommendation = ArrayList<String>()

    fun setData(data: List<String>?) {
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
    }

    override fun getItemCount() = listBooksRecommendation.size
}
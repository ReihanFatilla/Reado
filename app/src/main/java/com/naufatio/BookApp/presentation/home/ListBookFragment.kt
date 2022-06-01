package com.naufatio.BookApp.presentation.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.naufatio.BookApp.data.BooksResponse
import com.naufatio.BookApp.data.ItemsItem
import com.naufatio.BookApp.databinding.FragmentListBookBinding
import com.naufatio.BookApp.helper.OnItemClickCallback
import com.naufatio.BookApp.presentation.detail.DetailActivity
import com.naufatio.BookApp.presentation.home.adapter.BookTabbarAdapter

class ListBookFragment() : Fragment() {

    private var _binding: FragmentListBookBinding? = null
    private val binding get() = _binding as FragmentListBookBinding

    private var _viewModel: HomeViewModel? = null
    private val viewModel get() = _viewModel as HomeViewModel

    private lateinit var getCategory: String
    private lateinit var mAdapter: BookTabbarAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentListBookBinding.inflate(layoutInflater)
        _viewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        mAdapter = BookTabbarAdapter()

//        setupRecyclerView()

        getCategory = arguments?.getString(HomeFragment.VIEWPAGER_TITlE_KEY).toString()

        viewModel.getRandomBooksByCategory(getCategory)

        viewModel.booksResponse.observe(viewLifecycleOwner) {
            setUpRecyclerView(it.items)
        }


        return binding.root
    }

    private fun setUpRecyclerView(data: List<ItemsItem>?) {
        binding.rvHomeTabBar.apply {
            mAdapter.setData(data)
            adapter = mAdapter
            layoutManager = LinearLayoutManager(context)
            mAdapter.setOnItemClickCallback(object : OnItemClickCallback {
                override fun onItemClicked(item: ItemsItem) {
                    startActivity(
                        Intent(context, DetailActivity::class.java)
                    )
                }
            })
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
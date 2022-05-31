package com.naufatio.BookApp.presentation.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.naufatio.BookApp.R
import com.naufatio.BookApp.databinding.FragmentHomeBinding
import com.naufatio.BookApp.databinding.FragmentListBookBinding
import com.naufatio.BookApp.presentation.home.adapter.BookTabbarAdapter

class ListBookFragment() : Fragment() {

    private var _binding: FragmentListBookBinding? = null

    private val binding get() = _binding!!

    lateinit var getCategory: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentListBookBinding.inflate(layoutInflater)

        getCategory = arguments?.getString(HomeFragment.VIEWPAGER_TITlE_KEY).toString()

        setupRecyclerView()

        return binding.root
    }

    private fun setupRecyclerView() {
        binding.rvHomeTabBar.apply {
            adapter = BookTabbarAdapter()
            layoutManager = LinearLayoutManager(context)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
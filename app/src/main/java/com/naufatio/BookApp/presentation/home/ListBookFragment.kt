package com.naufatio.BookApp.presentation.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.naufatio.BookApp.R
import com.naufatio.BookApp.databinding.FragmentHomeBinding
import com.naufatio.BookApp.databinding.FragmentListBookBinding

class ListBookFragment() : Fragment() {

    private var _binding: FragmentListBookBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentListBookBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
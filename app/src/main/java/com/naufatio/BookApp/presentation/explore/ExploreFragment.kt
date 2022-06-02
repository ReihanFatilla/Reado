package com.naufatio.BookApp.presentation.explore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.naufatio.BookApp.databinding.FragmentExploreBinding

class ExploreFragment : Fragment() {

    private var _binding: FragmentExploreBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentExploreBinding.inflate(inflater, container, false)

        setUpSortByMenu()

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        binding.cvSortBy.visibility = View.GONE
    }

    private fun setUpSortByMenu() {
        binding.svBook.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                binding.apply {
                    cvSortBy.visibility = View.VISIBLE
                    tvSearchTitle.text = newText
                    tvSearchCategory.text = newText
                    tvSearchPublisher.text = newText
                    tvSearchAuthor.text = newText
                }
                return false
            }

        })
        binding.svBook.setOnQueryTextFocusChangeListener { _, b ->
            if (!b) {
                binding.cvSortBy.visibility = View.GONE
            } else {
                binding.cvSortBy.visibility = View.VISIBLE
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
package com.naufatio.BookApp.presentation.explore

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.naufatio.BookApp.data.ItemsItem
import com.naufatio.BookApp.databinding.FragmentExploreBinding

class ExploreFragment : Fragment() {

    private var _binding: FragmentExploreBinding? = null

    private val binding get() = _binding!!

    private var _viewModel: ExploreViewModel? = null
    private val viewModel get() = _viewModel as ExploreViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentExploreBinding.inflate(inflater, container, false)

        _viewModel = ViewModelProvider(this)[ExploreViewModel::class.java]

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
                newText?.let { searchByCategory(it) }
                Log.i("NewText", "onQueryTextChange: $newText")
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

    private fun searchByCategory(text: String) {
        binding.apply {
            cvSearchTitle.setOnClickListener {
                viewModel.searchBookInTitle(text)
            }
            cvSearchAuthor.setOnClickListener {
                viewModel.searchBookInAuthor(text)

            }
            cvSearchCategory.setOnClickListener {
                viewModel.searchBookInCategory(text)

            }
            cvSearchPublisher.setOnClickListener {
                viewModel.searchBookInPublisher(text)

            }
        }

        viewModel.booksResponse.observe(viewLifecycleOwner) {
            Log.i("ExploreFragment", "searchByCategory: ${it.items}")
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
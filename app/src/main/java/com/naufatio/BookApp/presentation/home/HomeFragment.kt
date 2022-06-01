package com.naufatio.BookApp.presentation.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import com.naufatio.BookApp.data.ItemsItem
import com.naufatio.BookApp.databinding.FragmentHomeBinding
import com.naufatio.BookApp.helper.constant
import com.naufatio.BookApp.presentation.home.adapter.BookRecommendationsAdapter


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!

    private val rvAdapter by lazy { (BookRecommendationsAdapter()) }

    private var _viewModel: HomeViewModel? = null
    private val viewModel get() = _viewModel as HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        _viewModel = ViewModelProvider(this)[HomeViewModel::class.java]

        val randomBookCategory = constant.BooksRecommendation.random()

        viewModel.getRandomBooks(randomBookCategory)

        viewModel.booksResponse.observe(viewLifecycleOwner) { setupRecyclerView(it.items) }

        val tabs = binding.tabLayout
        val viewPager = binding.viewpager
        tabs.setupWithViewPager(viewPager)
        setUpTabBar(viewPager)

        return binding.root
    }

    private fun setupRecyclerView(books: List<ItemsItem>?) {
        binding.rvHomeRecommendations.apply {
            val mAdapter = BookRecommendationsAdapter()
            mAdapter.setData(books)
            Log.i("Mainactivity", "setupRecyclerView: $books")
            adapter = mAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }
    }

    private fun setUpTabBar(viewPager: ViewPager) {
        val adapter = Adapter(childFragmentManager)
        adapter.addFragment("General")
        adapter.addFragment("Fiction")
        adapter.addFragment("Knowledge")
        adapter.addFragment("Novel")
        adapter.addFragment("Novel")
        adapter.addFragment("Novel")
        viewPager.adapter = adapter
    }


    class Adapter(manager: FragmentManager) : FragmentPagerAdapter(manager) {
        private val mFragmentList: MutableList<Fragment> = ArrayList()
        private val mFragmentTitleList: MutableList<String> = ArrayList()
        override fun getItem(position: Int): Fragment {
            return mFragmentList[position]
        }

        override fun getCount(): Int {
            return mFragmentList.size
        }

        fun addFragment(title: String) {
            var bundle = Bundle()
            val fragment = ListBookFragment()
            bundle.putString(VIEWPAGER_TITlE_KEY, title)
            fragment.arguments = bundle
            mFragmentList.add(fragment)
            mFragmentTitleList.add(title)
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return mFragmentTitleList[position]
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object{
        const val VIEWPAGER_TITlE_KEY = "ViewPager_Title_Key"
    }
}
package com.naufatio.BookApp.presentation.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.naufatio.BookApp.data.BooksResponse
import com.naufatio.BookApp.databinding.FragmentHomeBinding
import com.naufatio.BookApp.presentation.home.adapter.BookRecommendationsAdapter
import com.naufatio.BookApp.presentation.home.adapter.BookTabbarAdapter


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!

    private val rvAdapter by lazy { (BookRecommendationsAdapter()) }

    private var _viewModel: HomeViewModel? = null
    private val viewModel get() = _viewModel as HomeViewModel

    var booksResponse = MutableLiveData<List<BooksResponse>>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        _viewModel = ViewModelProvider(this)[HomeViewModel::class.java]

        viewModel.getData({
            booksResponse.value = it
            Log.i("Mainactivity", "onCreateView: $booksResponse")
        }, {
            Toast.makeText(context, "Error $it", Toast.LENGTH_SHORT).show()
        }, "book")

        val tabs = binding.tabLayout
        val viewPager = binding.viewpager
        tabs.setupWithViewPager(viewPager)
        setUpTabBar(viewPager)

        setupRecyclerView()

        return binding.root
    }

    private fun setupRecyclerView() {
        binding.rvHomeRecommendations.apply {
            adapter = BookTabbarAdapter()
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }
    }

    private fun setUpTabBar(viewPager: ViewPager) {
        val adapter = Adapter(childFragmentManager)
        adapter.addFragment( "General")
        adapter.addFragment( "Fiction")
        adapter.addFragment( "Knowledge")
        adapter.addFragment( "Novel")
        adapter.addFragment( "Novel")
        adapter.addFragment( "Novel")
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
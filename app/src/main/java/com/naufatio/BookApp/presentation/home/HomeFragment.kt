package com.naufatio.BookApp.presentation.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.naufatio.BookApp.data.ItemsItem
import com.naufatio.BookApp.databinding.FragmentHomeBinding
import com.naufatio.BookApp.helper.OnItemClickCallback
import com.naufatio.BookApp.helper.constant
import com.naufatio.BookApp.notification.NotificationService
import com.naufatio.BookApp.presentation.detail.DetailActivity
import com.naufatio.BookApp.presentation.home.adapter.BookRecommendationsAdapter


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!

    private var _viewModel: HomeViewModel? = null
    private val viewModel get() = _viewModel as HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        _viewModel = ViewModelProvider(this)[HomeViewModel::class.java]

        val getRandomBookCategories = constant.BooksRecommendation.random()

        viewModel.getRandomBooks(getRandomBookCategories)
        viewModel.booksResponse.observe(viewLifecycleOwner) { setupRecyclerView(it.items) }

        val id = viewModel.getRecentBookId().toString()
        viewModel.getBooksById(id)
        viewModel.recentBooksResponse.observe(viewLifecycleOwner){ setUpRecentViewedBook(it) }

//        NotificationService().createNotificationChannel(requireContext())
//        NotificationService().scheduleNotification(requireContext(), "iyaa", "okeee")

        setUpTabBarAndViewPager()

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        viewModel.recentBooksResponse.observe(viewLifecycleOwner){ setUpRecentViewedBook(it) }
    }

    private fun setUpTabBarAndViewPager() {
        val tabs = binding.tabLayout
        val viewPager = binding.viewpager
        tabs.setupWithViewPager(viewPager)
        setUpTabBar(viewPager)
    }

    private fun setUpRecentViewedBook(books: ItemsItem) {
        binding.apply {

            val title = books.volumeInfo?.title
            var image: String? = ""

            if (books.volumeInfo?.imageLinks?.large != null) {
                image = books.volumeInfo.imageLinks.large
            } else {
                image = books.volumeInfo?.imageLinks?.thumbnail
            }

            binding.tvRecentBookTitle.text = title
            Glide.with(this@HomeFragment)
                .load(image)
                .apply(RequestOptions())
                .override(500, 500)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH)
                .into(imgRecentBook)
        }

        binding.btnRecentBook.setOnClickListener {
            startActivity(
                Intent(context, DetailActivity::class.java)
                    .putExtra(constant.EXTRA_BOOK_INTENT, books)
            )
        }
    }

    private fun setupRecyclerView(books: List<ItemsItem>?) {
        binding.rvHomeRecommendations.apply {
            val mAdapter = BookRecommendationsAdapter()
            mAdapter.setData(books)
            adapter = mAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            mAdapter.setOnItemClickCallback(object : OnItemClickCallback {
                override fun onItemClicked(data: ItemsItem) {
                    startActivity(
                        Intent(context, DetailActivity::class.java)
                            .putExtra(constant.EXTRA_BOOK_INTENT, data)
                    )
                }
            })
        }
    }

    private fun setUpTabBar(viewPager: ViewPager) {
        val adapter = Adapter(childFragmentManager)
        adapter.addFragment("Mystery")
        adapter.addFragment("Technology")
        adapter.addFragment("Business")
        adapter.addFragment("Economy")
        adapter.addFragment("Politics")
        adapter.addFragment("Biography")
        adapter.addFragment("Thrillers")
        adapter.addFragment("Memoir")
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
            bundle.putString(constant.VIEWPAGER_TITlE_KEY, title)
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

}
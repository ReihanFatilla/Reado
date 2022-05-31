package com.naufatio.BookApp.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.naufatio.BookApp.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val tabs = binding.tabLayout
        val viewPager = binding.viewpager
        tabs.setupWithViewPager(viewPager)
        setUpTabBar(viewPager)

        return binding.root
    }

    private fun setUpTabBar(viewPager: ViewPager) {
        val adapter = Adapter(childFragmentManager)
        adapter.addFragment(ListBookFragment(), "General")
        adapter.addFragment(ListBookFragment(), "Fiction")
        adapter.addFragment(ListBookFragment(), "Knowledge")
        adapter.addFragment(ListBookFragment(), "Novel")
        adapter.addFragment(ListBookFragment(), "Novel")
        adapter.addFragment(ListBookFragment(), "Novel")
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

        fun addFragment(fragment: Fragment, title: String) {
            var bundle = Bundle()
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
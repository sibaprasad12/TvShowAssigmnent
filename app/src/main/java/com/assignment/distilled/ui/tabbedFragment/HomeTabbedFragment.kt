package com.assignment.distilled.ui.tabbedFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.assignment.distilled.R
import com.assignment.distilled.databinding.FragmentTabbedFragmentBinding
import com.assignment.distilled.ui.favouriteTvShow.FavouriteTvShowFragment
import com.assignment.distilled.ui.tvShow.TvShowFragment
import com.google.android.material.tabs.TabLayoutMediator


/**
 * Created by Sibaprasad Mohanty on 22/07/21.
 * Spm Limited
 * sp.dobest@gmail.com
 */

class HomeTabbedFragment : Fragment() {

    lateinit var binding: FragmentTabbedFragmentBinding
    val tvShowTabArray = arrayOf(
        "Tv Shows",
        "Favourite Tv Show"
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_tabbed_fragment, container, false
        )
        val view: View = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewPager()
    }

    private fun setupViewPager() {
        val adapter = TvShowViewPagerAdapter(activity as FragmentActivity)
        adapter.setTabbedFragment(arrayOf(TvShowFragment(), FavouriteTvShowFragment()))
        binding.tvShowViewPager.adapter = adapter
        binding.tvShowViewPager.currentItem = 0

        TabLayoutMediator(binding.tabLayoutTvSHow, binding.tvShowViewPager) { tab, position ->
            tab.text = tvShowTabArray[position]
        }.attach()
    }
}
package com.assignment.distilled.ui.tabbedFragment

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter


/**
 * Created by Sibaprasad Mohanty on 22/07/21.
 * Spm Limited
 * sp.dobest@gmail.com
 */

class TvShowViewPagerAdapter(fragmentManager: FragmentActivity) :
    FragmentStateAdapter(fragmentManager) {

    private val arrayListFragment: ArrayList<Fragment> = ArrayList()

    fun setTabbedFragment(argsFragment: Array<Fragment>) {
        arrayListFragment.addAll(argsFragment)
    }

    override fun getItemCount(): Int = arrayListFragment.size

    override fun createFragment(position: Int): Fragment = arrayListFragment[position]
}
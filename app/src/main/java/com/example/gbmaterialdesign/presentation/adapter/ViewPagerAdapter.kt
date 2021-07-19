package com.example.gbmaterialdesign.presentation.adapter

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.gbmaterialdesign.presentation.view.EarthFragment
import com.example.gbmaterialdesign.presentation.view.MarsFragment

class ViewPagerAdapter(manager: FragmentManager) : FragmentStatePagerAdapter(manager) {
    private val fragments = arrayOf(EarthFragment(), MarsFragment())
    private val fragmentsTitles = arrayOf(EarthFragment.routeName, MarsFragment.routeName)

    override fun getCount() = fragments.size

    override fun getItem(position: Int) = fragments[position]

    override fun getPageTitle(position: Int) = fragmentsTitles[position]
}

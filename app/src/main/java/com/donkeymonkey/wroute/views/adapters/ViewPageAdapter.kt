package com.donkeymonkey.wroute.views.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.view.ViewGroup
import android.util.SparseArray
import android.support.v4.app.FragmentStatePagerAdapter
import com.donkeymonkey.wroute.views.fragments.CreateWrouteMainFragment
import com.donkeymonkey.wroute.views.fragments.CreateWrouteMapFragment
import com.donkeymonkey.wroute.views.fragments.CreateWrouteStopFragment


class ViewPageAdapter(fragmentManager: FragmentManager) : FragmentStatePagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment? {

        return registeredFragments.get(position)
    }

    override fun getCount(): Int {
        return 3
    }

    fun addFragment(position: Int, fragment: Fragment) {
        registeredFragments.put(position, fragment)
    }

    // Sparse array to keep track of registered fragments in memory
    private val registeredFragments = SparseArray<Fragment>()

    // Returns the fragment for the position (if instantiated)
    fun getRegisteredFragment(position: Int): Fragment {
        return registeredFragments.get(position)
    }




}
package com.donkeymonkey.wroute.views.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.donkeymonkey.wroute.R

class CreateWrouteStopFragment: BaseFragment() {

    fun newInstance(): Fragment {
        return CreateWrouteStopFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_create_stops, container, false)

        return view

    }
}
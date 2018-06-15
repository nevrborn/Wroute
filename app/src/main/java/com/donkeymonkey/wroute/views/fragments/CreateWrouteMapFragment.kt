package com.donkeymonkey.wroute.views.fragments

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.donkeymonkey.wroute.R
import com.donkeymonkey.wroute.databinding.FragmentCreateMapBinding
import com.donkeymonkey.wroute.viewmodels.CreateWrouteViewModel

class CreateWrouteMapFragment: BaseFragment() {

    lateinit var viewModel: CreateWrouteViewModel
    lateinit var binding: FragmentCreateMapBinding

    fun newInstance(): Fragment {
        return CreateWrouteMapFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        viewModel = ViewModelProviders.of(activity!!).get(CreateWrouteViewModel::class.java)

        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_create_map, container, false)
        binding.viewModel = viewModel
        binding.wroute = viewModel.getWroute().value
        binding.fragment = this
        binding.setLifecycleOwner(this)

        return binding.root

    }
}
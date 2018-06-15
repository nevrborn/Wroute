package com.donkeymonkey.wroute.views.fragments

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.donkeymonkey.wroute.R
import com.donkeymonkey.wroute.databinding.FragmentFilterBinding
import com.donkeymonkey.wroute.viewmodels.MainViewModel

class FilterFragment: BaseFragment() {

    lateinit var viewModel: MainViewModel
    lateinit var binding: FragmentFilterBinding

    fun newInstance(): Fragment {
        return FilterFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_filter, container, false)

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_filter, container, false)
        binding.viewModel = viewModel
        binding.fragment = this
        binding.setLifecycleOwner(this)

        return binding.root
    }
}
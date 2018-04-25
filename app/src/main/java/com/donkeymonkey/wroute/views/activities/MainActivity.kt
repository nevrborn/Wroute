package com.donkeymonkey.wroute.views.activities

import `in`.galaxyofandroid.spinerdialog.SpinnerDialog
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.view.ViewPager
import com.donkeymonkey.wroute.R
import com.donkeymonkey.wroute.databinding.ActivityMainBinding
import com.donkeymonkey.wroute.viewmodels.MainViewModel
import com.donkeymonkey.wroute.views.adapters.ViewPageAdapter
import com.donkeymonkey.wroute.views.fragments.*
import kotlinx.android.synthetic.main.activity_main.view.*
import android.databinding.adapters.TextViewBindingAdapter.setText
import android.widget.Toast
import `in`.galaxyofandroid.spinerdialog.OnSpinerItemClick



class MainActivity : BaseActivity() {
    lateinit var viewModel: MainViewModel
    lateinit var binding: ActivityMainBinding

    var currentPage: Int = 0

    private lateinit var mainPageAdapter: ViewPageAdapter
    private lateinit var citySpinnerDialog: SpinnerDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.activity = this
        binding.viewModel = viewModel

        configureToolBar(binding.root)

        setupCitySpinner()
        setupViewPager()

        binding.pager.currentItem = 1

    }

    private fun setupCitySpinner() {

        citySpinnerDialog = SpinnerDialog(this, viewModel.items, "Select or Search City", R.style.DialogAnimations_SmileWindow, "Close")

        citySpinnerDialog.bindOnSpinerListener(OnSpinerItemClick { item, position ->
            Toast.makeText(this, "$item  $position", Toast.LENGTH_SHORT).show()
        })
    }

    private fun setupViewPager() {

        mainPageAdapter = ViewPageAdapter(supportFragmentManager)

        mainPageAdapter.addFragment(0, AgendaFragment().newInstance())
        mainPageAdapter.addFragment(1, MainFragment().newInstance())
        mainPageAdapter.addFragment(2, FilterFragment().newInstance())

        binding.pager.adapter = mainPageAdapter

        actionBarAgenda?.setOnClickListener {
            binding.pager.currentItem = 0
        }

        actionBarCity?.setOnClickListener {
            if (binding.pager.currentItem != 1) {
                binding.pager.currentItem = 1
            } else {
                citySpinnerDialog.showSpinerDialog();
            }
        }

        actionBarFilter?.setOnClickListener {
            binding.pager.currentItem = 2
        }

        binding.pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {

            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
            }
        })
    }

}
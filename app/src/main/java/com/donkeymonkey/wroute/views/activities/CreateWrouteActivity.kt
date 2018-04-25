package com.donkeymonkey.wroute.views.activities

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.view.View
import com.donkeymonkey.wroute.R
import com.donkeymonkey.wroute.databinding.ActivityCreateWrouteBinding
import com.donkeymonkey.wroute.viewmodels.CreateWrouteViewModel
import com.donkeymonkey.wroute.views.adapters.ViewPageAdapter
import com.donkeymonkey.wroute.views.dialogs.StopDialog
import com.donkeymonkey.wroute.views.fragments.CreateWrouteMainFragment
import com.donkeymonkey.wroute.views.fragments.CreateWrouteMapFragment
import com.donkeymonkey.wroute.views.fragments.CreateWrouteStopFragment

class CreateWrouteActivity : BaseActivity() {
    lateinit var viewModel: CreateWrouteViewModel
    lateinit var binding: ActivityCreateWrouteBinding

    var currentPage: Int = 0

    private lateinit var pagerAdapter: ViewPageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(CreateWrouteViewModel::class.java)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_create_wroute)
        binding.activity = this
        binding.viewModel = viewModel

        configureLightToolbar(binding.root)

        pagerAdapter = ViewPageAdapter(supportFragmentManager)

        pagerAdapter.addFragment(0, CreateWrouteMainFragment().newInstance())
        pagerAdapter.addFragment(1, CreateWrouteStopFragment().newInstance())
        pagerAdapter.addFragment(2, CreateWrouteMapFragment().newInstance())

        binding.pager.adapter = pagerAdapter

        binding.pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {

            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                currentPage = position
                showOrHideButtons(currentPage)
            }

            override fun onPageSelected(position: Int) {
            }
        })

    }

    fun changeFragment(isNext: Boolean) {

        if (isNext && currentPage < 2) {
            currentPage += 1
            binding.pager.currentItem = currentPage
        } else {
            currentPage -= 1
            binding.pager.currentItem = currentPage
        }

        binding.pager.currentItem = currentPage
        showOrHideButtons(currentPage)
//        Log.d("Wroute Name", viewModel.wroute.name)
//        Log.d("Wroute Description", viewModel.wroute.description)

    }

    fun showOrHideButtons(postition: Int) {

        when (postition) {
            0 -> {
               binding.buttonPrevious.visibility = View.GONE
               binding.buttonAddStop.visibility = View.GONE
               binding.buttonNext.visibility = View.VISIBLE
            }
            1 -> {
                binding.buttonPrevious.visibility = View.VISIBLE
                binding.buttonAddStop.visibility = View.VISIBLE
                binding.buttonNext.visibility = View.VISIBLE
            }
            2 -> {
                binding.buttonPrevious.visibility = View.VISIBLE
                binding.buttonAddStop.visibility = View.VISIBLE
                binding.buttonNext.visibility = View.GONE
            }
        }
    }

    fun addStop() {
        StopDialog.newInstance()
    }
}
package com.donkeymonkey.wroute.views.activities

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.Button
import com.donkeymonkey.wroute.R
import com.donkeymonkey.wroute.databinding.ActivityCreateWrouteBinding
import com.donkeymonkey.wroute.defines.Defines
import com.donkeymonkey.wroute.models.Wroute
import com.donkeymonkey.wroute.viewmodels.CreateWrouteViewModel
import com.donkeymonkey.wroute.views.adapters.ViewPageAdapter
import com.donkeymonkey.wroute.views.dialogs.StopDialog
import com.donkeymonkey.wroute.views.fragments.CreateWrouteMainFragment
import com.donkeymonkey.wroute.views.fragments.CreateWrouteMapFragment
import com.donkeymonkey.wroute.views.fragments.CreateWrouteStopFragment
import java.util.*

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
        binding.setLifecycleOwner(this)

        val toolbar = configureCreateWrouteToolbar(binding.root)

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

    fun configureCreateWrouteToolbar(view: View) {
        val toolbar = view.findViewById<Toolbar>(R.id.toolbar_light)

        if (toolbar != null) {
            setSupportActionBar(toolbar)

            val actionbarLayout = layoutInflater.inflate(R.layout.toolbar_light, null)
            supportActionBar!!.setCustomView(actionbarLayout)

            supportActionBar!!.setDisplayHomeAsUpEnabled(false)
            supportActionBar!!.setDisplayShowTitleEnabled(false)
            supportActionBar!!.setDisplayShowCustomEnabled(true)

            val actionBarCancel = findViewById<Button>(R.id.button_cancel)
            val actionBarSave = findViewById<Button>(R.id.button_save)

            actionBarCancel.setOnClickListener {
                cancel()
            }
            actionBarSave.setOnClickListener {
                saveWroute(viewModel.wroute.value)
            }
        }
    }

    fun saveWroute(wroute: Wroute?) {

        if (wroute != null) {

            wroute.creatorId = firebaseAuthHelper.mCurrentUser?.uid
            wroute.createDate = Date().time

            firebaseDBHelper.storeDocument(Defines.FirebaseDB.Wroutes, wroute)
            cancel()
        }

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
        val stopDialog = StopDialog.newInstance()
        stopDialog.show(supportFragmentManager, "hello")
    }


}
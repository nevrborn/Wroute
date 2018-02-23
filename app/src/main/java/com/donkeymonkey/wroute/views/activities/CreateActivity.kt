package com.donkeymonkey.wroute.views.activities

import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import com.donkeymonkey.wroute.R

class WrouteCreateActivity : AppCompatActivity() {

    private var mWrouteName: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_wroute_tab)

        mWrouteName = findViewById(R.id.editText_name_of_wroute) as EditText
        //mWrouteName.setText("Test Wroute Name");
        mWrouteName!!.isSelected = false

        val tabLayout = findViewById(R.id.tab_layout) as TabLayout
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.newWroute_info)))
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.newWroute_stops)))
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.newWroute_map)))
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.newWroute_finish)))
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL)

        val viewPager = findViewById(R.id.pager) as ViewPager
        val adapter = WrouteCreatePageAdapter(supportFragmentManager, tabLayout.getTabCount())
        viewPager.adapter = adapter
        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
        tabLayout.setOnTabSelectedListener(object : TabLayout.OnTabSelectedListener() {
            fun onTabSelected(tab: TabLayout.Tab) {
                viewPager.currentItem = tab.getPosition()
            }

            fun onTabUnselected(tab: TabLayout.Tab) {

            }

            fun onTabReselected(tab: TabLayout.Tab) {

            }
        })

        mWrouteName!!.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

            }

            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

            }

            override fun afterTextChanged(editable: Editable) {
                mWroute!!.setWrouteName(editable.toString())
            }
        })
    }

    companion object {

        var mWroute: Wroute? = null

        val wrouteInstance: Wroute
            get() {
                if (mWroute == null) {
                    mWroute = Wroute()
                }

                return mWroute
            }
    }


}
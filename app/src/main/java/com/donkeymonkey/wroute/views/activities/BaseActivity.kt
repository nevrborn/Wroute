package com.donkeymonkey.wroute.views.activities

import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import com.donkeymonkey.wroute.R
import com.donkeymonkey.wroute.helpers.*
import com.yalantis.guillotine.animation.GuillotineAnimation
import kotlinx.android.synthetic.main.toolbar_main.*

abstract class BaseActivity : AppCompatActivity() {
    val navigationHelper = NavigationHelper(this)
    val interactionHelper = InteractionHelper(this)
//    val analyticsHelper = AnalyticsHelper(this)
    val firebaseAuthHelper = FireBaseAuthHelper(this)
    val firebaseDBHelper = FireBaseDBHelper(this)
    val prefsHelper = PrefsHelper(this)

    var actionBarMenu: View? = null
    var actionBarAdd: View? = null
    var actionBarAgenda: View? = null
    var actionBarCity: View? = null
    var actionBarFilter: View? = null


    fun configureToolBar(view: View) {
        var toolbar = view.findViewById<Toolbar>(R.id.toolbar)

        val hambugerMenu = findViewById<ImageView>(R.id.toolbar_menu)
        val root = findViewById<FrameLayout>(R.id.main_root)
        val guillotineMenu = LayoutInflater.from(this).inflate(R.layout.menu, null)
        root.addView(guillotineMenu)

        if (toolbar != null) {
            setSupportActionBar(toolbar)

            val actionbarLayout = layoutInflater.inflate(R.layout.toolbar_main, null)
            supportActionBar!!.setCustomView(actionbarLayout)

            supportActionBar!!.setDisplayHomeAsUpEnabled(false)
            supportActionBar!!.setDisplayShowTitleEnabled(false)
            supportActionBar!!.setDisplayShowCustomEnabled(true)

            actionBarMenu = findViewById<ImageView>(R.id.toolbar_menu)
            actionBarAdd = findViewById<ImageView>(R.id.toolbar_add)
            actionBarAgenda = findViewById<ImageView>(R.id.toolbar_agenda)
            actionBarCity = findViewById<TextView>(R.id.toolbar_city)
            actionBarFilter = findViewById<ImageView>(R.id.toolbar_filter)

            actionBarAdd?.setOnClickListener(View.OnClickListener {
                addRouteAction()
            })

        }

        val menuAnimation =GuillotineAnimation.GuillotineBuilder(guillotineMenu, guillotineMenu.findViewById(R.id.toolbar_menu), hambugerMenu)
                .setActionBarViewForAnimation(toolbar)
                .setClosedOnStart(true)
                .build()

        val hamburger = guillotineMenu.findViewById<ImageView>(R.id.toolbar_menu)
        hamburger.setOnClickListener { menuAnimation.close() }
    }

    fun configureLightToolbar(view: View) {
        var toolbar = view.findViewById<Toolbar>(R.id.toolbar_light)

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
                saveWroute()
            }
        }
    }

    fun addRouteAction() {
        navigationHelper.openCreateWroute()
    }

    fun changeCityAction() {

    }


    fun saveWroute() {

    }

    fun cancel() {
        finish()
    }

}
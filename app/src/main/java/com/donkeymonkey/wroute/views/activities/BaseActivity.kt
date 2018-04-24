package com.donkeymonkey.wroute.views.activities

import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.donkeymonkey.wroute.R
import com.donkeymonkey.wroute.helpers.*

abstract class BaseActivity : AppCompatActivity() {
    val navigationHelper = NavigationHelper(this)
    val interactionHelper = InteractionHelper(this)
//    val analyticsHelper = AnalyticsHelper(this)
    val firebaseAuthHelper = FireBaseAuthHelper(this)
    val firebaseDBHelper = FireBaseDBHelper(this)
    val prefsHelper = PrefsHelper(this)


    fun configureToolBar(view: View) {
        var toolbar = view.findViewById<Toolbar>(R.id.toolbar)

        if (toolbar != null) {
            setSupportActionBar(toolbar);
            supportActionBar!!.setDisplayHomeAsUpEnabled(true);

            val actionbarLayout = layoutInflater.inflate(R.layout.toolbar_main, null)
            supportActionBar!!.setCustomView(actionbarLayout)

            supportActionBar!!.setDisplayHomeAsUpEnabled(false)
            supportActionBar!!.setDisplayShowTitleEnabled(false)
            supportActionBar!!.setDisplayShowCustomEnabled(true)

            val actionBarMenu = findViewById<ImageView>(R.id.toolbar_menu)
            val actionBarAdd = findViewById<ImageView>(R.id.toolbar_add)
            val actionBarAgenda = findViewById<ImageView>(R.id.toolbar_agenda)
            val actionBarCity = findViewById<TextView>(R.id.toolbar_city)
            val actionBarFilter = findViewById<ImageView>(R.id.toolbar_filter)

            actionBarMenu.setOnClickListener {
                showMenuAction()
            }
            actionBarAdd.setOnClickListener {
                addRouteAction()
            }
            actionBarAgenda.setOnClickListener {
                showAgendaAction()
            }
            actionBarCity.setOnClickListener {
                changeCityAction()
            }
            actionBarFilter.setOnClickListener {
                showFilterAction()
            }
        }
    }

    fun showMenuAction() {
        Log.d("Toolbar:", "Show Menu")
    }

    fun addRouteAction() {
        Log.d("Toolbar:", "Add Route")
    }

    fun showAgendaAction() {
        Log.d("Toolbar:", "Show Agenda")
    }

    fun changeCityAction() {
        Log.d("Toolbar:", "Change City")
    }

    fun showFilterAction() {
        Log.d("Toolbar:", "Show Filter")
    }

}
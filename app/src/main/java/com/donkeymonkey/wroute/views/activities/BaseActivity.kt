package com.donkeymonkey.wroute.views.activities

import android.support.v7.app.AppCompatActivity
import com.donkeymonkey.wroute.helpers.*

abstract class BaseActivity : AppCompatActivity() {
    val navigationHelper = NavigationHelper(this)
    val interactionHelper = InteractionHelper(this)
//    val analyticsHelper = AnalyticsHelper(this)
    val firebaseAuthHelper = FireBaseAuthHelper(this)
    val firebaseDBHelper = FireBaseDBHelper(this)
    val prefsHelper = PrefsHelper(this)
}
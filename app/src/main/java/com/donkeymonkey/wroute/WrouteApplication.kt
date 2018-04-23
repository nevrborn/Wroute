package com.donkeymonkey.wroute

import android.app.Application
import com.donkeymonkey.wroute.helpers.PrefsHelper


class WrouteApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        //boxStore = .builder().androidContext(this).build()
        prefsHelper = PrefsHelper(this)

    }

    companion object {
        val SHARED_PREFS = "WrouteSharedPrefs"
        lateinit var prefsHelper: PrefsHelper
    }
}
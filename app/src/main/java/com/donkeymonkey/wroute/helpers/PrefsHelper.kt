package com.donkeymonkey.wroute.helpers

import android.content.Context
import android.content.SharedPreferences
import com.donkeymonkey.wroute.WrouteApplication

class PrefsHelper(private val context: Context) {
    lateinit var sharedPreferences: SharedPreferences

    var currentCityId
        get() = getStringPref("current_city_id", "")
        set(value) = setStringPref("current_city_id", value)

    var userId
        get() = getStringPref("user_id", "")
        set(value) = setStringPref("user_id", value)

    fun getBooleanPref(key: String, default: Boolean): Boolean {
        sharedPreferences = context.getSharedPreferences(WrouteApplication.SHARED_PREFS, Context.MODE_PRIVATE)
        return sharedPreferences.getBoolean(key, default)
    }

    fun setBooleanPref(key: String, value: Boolean) {
        sharedPreferences = context.getSharedPreferences(WrouteApplication.SHARED_PREFS, Context.MODE_PRIVATE)
        sharedPreferences.edit().putBoolean(key, value).apply()
    }

    fun getStringPref(key: String, default: String): String {
        sharedPreferences = context.getSharedPreferences(WrouteApplication.SHARED_PREFS, Context.MODE_PRIVATE)
        return sharedPreferences.getString(key, default)
    }

    fun setStringPref(key: String, value: String) {
        sharedPreferences = context.getSharedPreferences(WrouteApplication.SHARED_PREFS, Context.MODE_PRIVATE)
        sharedPreferences.edit().putString(key, value).apply()
    }

    fun reset() {
        sharedPreferences = context.getSharedPreferences(WrouteApplication.SHARED_PREFS, Context.MODE_PRIVATE)
        sharedPreferences.edit().clear().apply()
    }
}
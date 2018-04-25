package com.donkeymonkey.wroute.views.fragments

import android.content.Context
import android.support.v4.app.Fragment
import com.donkeymonkey.wroute.helpers.*
import com.google.android.gms.common.api.internal.LifecycleFragment

abstract class BaseFragment() : Fragment() {

    lateinit var interactionHelper: InteractionHelper
    //    val analyticsHelper = AnalyticsHelper(this)
    lateinit var  firebaseAuthHelper: FireBaseAuthHelper
    lateinit var  firebaseDBHelper: FireBaseDBHelper
    lateinit var  prefsHelper: PrefsHelper

    fun setUpHelpers(context: Context) {
        interactionHelper = InteractionHelper(context)
        //    val analyticsHelper = AnalyticsHelper(this)
        firebaseAuthHelper = FireBaseAuthHelper(context)
        firebaseDBHelper = FireBaseDBHelper(context)
        prefsHelper = PrefsHelper(context)
    }
}
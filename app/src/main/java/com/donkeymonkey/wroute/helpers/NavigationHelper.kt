package com.donkeymonkey.wroute.helpers

import android.app.Activity
import android.content.Intent
import com.donkeymonkey.wroute.views.activities.BaseActivity
import com.donkeymonkey.wroute.views.activities.LoginActivity
import com.donkeymonkey.wroute.views.activities.LoginProfileActivity
import com.donkeymonkey.wroute.views.activities.MainActivity
import com.donkeymonkey.wroute.views.fragments.BaseFragment

class NavigationHelper(private val activity: Activity) {
    fun openMainActivity() = navigate(MainActivity::class.java, true)
    fun openLogin() = navigate(LoginActivity::class.java, true)
    fun openLoginProfile() = navigate(LoginProfileActivity::class.java, true)

    fun navigate(destination: Class<*>, clearTop: Boolean) {
        val intent = Intent(activity, destination)
        if (clearTop) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }
        activity.startActivity(intent)
    }

    fun loadFragment(activity: BaseActivity, container: Int, fragment: BaseFragment, addToBackStack: Boolean, clearBackstack: Boolean) {
        val fragmentTransaction = activity.supportFragmentManager.beginTransaction()
        if (addToBackStack) {
            fragmentTransaction.addToBackStack(null)
//            fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right)
        } else {
//            activity.getSupportFragmentManager().popBackStack(null, activity.supportFragmentManager.POP_BACK_STACK_INCLUSIVE)
        }
        fragmentTransaction.replace(container, fragment)
        fragmentTransaction.commit()
    }

    companion object {
        private val TAG = "BIBNavigationHelper"
    }

}
package com.donkeymonkey.wroute.views.activities

import android.app.Activity
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.EditText
import android.widget.Toast
import com.donkeymonkey.wroute.R
import com.donkeymonkey.wroute.databinding.ActivityLoginBinding
import com.donkeymonkey.wroute.helpers.*
import com.donkeymonkey.wroute.viewmodels.LoginViewModel
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.ErrorCodes
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.util.*

class LoginActivity: BaseActivity() {
    lateinit var viewModel: LoginViewModel
    lateinit var binding: ActivityLoginBinding

    private var userEmailEditText: EditText? = null
    private var passwordEditText: EditText? = null

    val RC_SIGN_IN = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.activity = this
        binding.viewModel = viewModel

        firebaseAuthHelper.initialiseFirebase(this)
        prefsHelper.currentCityId = "Amsterdam"
        firebaseDBHelper.updateRefs(prefsHelper.currentCityId)
    }

    fun nextAction() {

        val isUserSignedIn = firebaseAuthHelper.mAuth?.currentUser != null

        if (!isUserSignedIn) {
            login()
        } else {
            prefsHelper.userId = firebaseAuthHelper.mAuth?.currentUser?.uid.let { it } ?: ""
            navigationHelper.openMainActivity()
        }
    }

    private fun login() {
        val params = Bundle()
        params.putString(AuthUI.EXTRA_DEFAULT_COUNTRY_CODE, "nl")

        val phoneConfigWithDefaultNumber = AuthUI.IdpConfig.Builder(AuthUI.PHONE_VERIFICATION_PROVIDER)
                .setParams(params)
                .build()
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(
                                Arrays.asList(phoneConfigWithDefaultNumber))
                        .build(),
                RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // RC_SIGN_IN is the request code you passed into 	  					           // startActivityForResult(...)
        // when starting the sign in flow.
        if (requestCode == RC_SIGN_IN) {
            val response = IdpResponse.fromResultIntent(data)
            when {
                resultCode == Activity.RESULT_OK -> {
                    // Successfully signed in
                    interactionHelper.toast("Login successful")
                    navigationHelper.openLoginProfile()
                    return
                }
                response == null -> {
                    // Sign in failed
                    // User pressed back button
                    interactionHelper.toast("Sign in cancelled")
                    return
                }
                response.errorCode == ErrorCodes.NO_NETWORK -> {
                    // Sign in failed
                    //No Internet Connection
                    interactionHelper.toast("No Internet connection")
                    return
                }
                response.errorCode == ErrorCodes.UNKNOWN_ERROR -> {
                    // Sign in failed
                    //Unknown Error
                    interactionHelper.toast("Unknown error")
                    return
                }
                else -> {
                    interactionHelper.toast("Unknown Response")
                }
            }
        }
    }

}

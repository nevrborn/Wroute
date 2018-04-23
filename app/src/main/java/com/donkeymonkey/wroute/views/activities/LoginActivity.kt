package com.donkeymonkey.wroute.views.activities

import android.app.Activity
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import com.donkeymonkey.wroute.R
import com.donkeymonkey.wroute.databinding.ActivityLoginBinding
import com.donkeymonkey.wroute.helpers.InteractionHelper
import com.donkeymonkey.wroute.viewmodels.LogInViewModel
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.ErrorCodes
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.util.*

class LogInActicity : BaseActivity() {
    lateinit var viewModel: LogInViewModel
    lateinit var binding: ActivityLoginBinding

    private var userEmailEditText: EditText? = null
    private var passwordEditText: EditText? = null

    val RC_SIGN_IN = 1

    //Firebase references
    private var mDatabaseReference: DatabaseReference? = null
    private var mDatabase: FirebaseDatabase? = null
    private var mAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(LogInViewModel::class.java)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.activity = this
        binding.viewModel = viewModel

        initialise()

    }

    private fun initialise() {

        FirebaseApp.initializeApp(this)
        mDatabase = FirebaseDatabase.getInstance()
        mDatabaseReference = mDatabase!!.reference!!.child("Users")
        mAuth = FirebaseAuth.getInstance()


    }

    fun loginAction() {

        val isUserSignedIn = FirebaseAuth.getInstance().currentUser != null

        if (!isUserSignedIn) login()
    }


    private fun login() {
        val params = Bundle()
        params.putString(AuthUI.EXTRA_DEFAULT_COUNTRY_CODE, "ng")
        params.putString(AuthUI.EXTRA_DEFAULT_NATIONAL_NUMBER, "23456789")

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
                    Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show()
                    return
                }
                response == null -> {
                    // Sign in failed
                    // User pressed back button
                    Toast.makeText(this, "Sign in cancelled", Toast.LENGTH_SHORT).show()
                    return
                }
                response.errorCode == ErrorCodes.NO_NETWORK -> {
                    // Sign in failed
                    //No Internet Connection
                    Toast.makeText(this, "No Internet connection", Toast.LENGTH_SHORT).show()
                    return
                }
                response.errorCode == ErrorCodes.UNKNOWN_ERROR -> {
                    // Sign in failed
                    //Unknown Error
                    Toast.makeText(this, "Unknown error", Toast.LENGTH_SHORT).show()
                    return
                }
                else -> {
                    Toast.makeText(this, "Unknown Response", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }



}

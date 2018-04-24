package com.donkeymonkey.wroute.views.activities

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.donkeymonkey.wroute.R
import com.donkeymonkey.wroute.databinding.ActivityLoginProfileBinding
import com.donkeymonkey.wroute.helpers.*
import com.donkeymonkey.wroute.models.User
import com.donkeymonkey.wroute.viewmodels.LoginProfileViewModel
import io.reactivex.functions.Action

class LoginProfileActivity: BaseActivity() {
    lateinit var viewModel: LoginProfileViewModel
    lateinit var binding: ActivityLoginProfileBinding

    var user = User()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(LoginProfileViewModel::class.java)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login_profile)
        binding.activity = this
        binding.viewModel = viewModel

        firebaseAuthHelper.initialiseFirebase(this)

    }

    fun nextAction() {

        viewModel.uid = firebaseAuthHelper.mCurrentUser?.uid

        firebaseDBHelper.storeProfile(viewModel.getNewUser()).subscribe()

        navigationHelper.openMainActivity()

    }


}
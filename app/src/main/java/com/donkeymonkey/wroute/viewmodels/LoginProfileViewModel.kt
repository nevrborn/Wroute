package com.donkeymonkey.wroute.viewmodels

import android.graphics.Bitmap
import com.donkeymonkey.wroute.models.User

class LoginProfileViewModel: BaseViewModel() {

    var uid: String? = null
    var profilePicture: Bitmap? = null
    var firstName: String = ""
    var lastName: String? = null


    fun getNewUser(): User {

        return User(uid, firstName, lastName, profilePicture)

    }

}
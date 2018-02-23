package com.donkeymonkey.wroute.models

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class User// constructor
private constructor(firebaseUser: FirebaseUser) {

    var name: String?
        get() = sName
        set(name) {
            User.sName = name
        }

    var email: String?
        get() = sEmail
        set(email) {
            User.sEmail = email
        }

    val uid: String
        get() = sUid


    init {
        sName = firebaseUser.getDisplayName()
        sEmail = firebaseUser.getEmail()
        sUid = firebaseUser.getUid()
        setIsLoggedIn(true)
    }

    private fun setIsLoggedIn(isLoggedIn: Boolean) {
        val sIsLoggedIn = isLoggedIn
    }

    companion object {

        // Name, email address, and profile photo Url
        private var sName: String? = null
        private var sEmail: String? = null

        // The user's ID, unique to the Firebase project. Do NOT use this value to
        // authenticate with your backend server, if you have one. Use
        // FirebaseUser.getToken() instead.
        private var sUid: String = ""

        private var mUser: User? = null

        fun get(): User? {
            return mUser
        }

        fun set() {
            // if we have a loggedin user, set mUser
            val firebaseUser = FirebaseAuth.getInstance().getCurrentUser()
            if (firebaseUser != null) {
                mUser = User(firebaseUser)
            }
        }

        fun signOut() {
            FirebaseAuth.getInstance().signOut()
            mUser = null
        }
    }

}


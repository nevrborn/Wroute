package com.donkeymonkey.wroute.helpers

import android.content.Context
import com.donkeymonkey.wroute.defines.Defines
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore

abstract class FirebaseBaseHelper() {

    var mAuth: FirebaseAuth? = null

    var mDatabase: FirebaseFirestore? = null

    var mCurrentUser: FirebaseUser? = null

    fun initialiseFirebase(context: Context) {

        FirebaseApp.initializeApp(context)
        mAuth = FirebaseAuth.getInstance()

        mDatabase = FirebaseFirestore.getInstance()

        mCurrentUser = mAuth!!.currentUser

    }

}
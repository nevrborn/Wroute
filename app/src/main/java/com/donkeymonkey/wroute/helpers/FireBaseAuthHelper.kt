package com.donkeymonkey.wroute.helpers

import android.util.Log
import android.widget.Toast
import com.donkeymonkey.wroute.models.User
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class FireBaseHelper {

    private val TAG = "FirebaseHelper"

    private var mDatabaseReference: DatabaseReference? = null
    private var mDatabase: FirebaseDatabase? = null
    private var mAuth: FirebaseAuth? = null

    // Wroute Database Refs
    private var mUserDbRef: DatabaseReference? = null
    private var mWrouteDbRef: DatabaseReference? = null


    public fun initialiseFFirebase() {

        mDatabase = FirebaseDatabase.getInstance()
        mDatabaseReference = mDatabase!!.reference!!
        mAuth = FirebaseAuth.getInstance()

        mUserDbRef = mDatabaseReference!!.child("Users")
        mWrouteDbRef = mDatabaseReference!!.child("Wroutes")

    }

    /* AUTHENTICATION */

    public fun createNewUserAccount(user: User, password: String) {

        mAuth!!.createUserWithEmailAndPassword(user.email!!, password)
                .addOnCompleteListener(OnCompleteListener { task ->

                    if (task.isSuccessful) {
                        Log.d(TAG, "createUserWithEmail:success")

                        verifyEmail()

                        user.uid = mAuth!!.currentUser!!.uid

                        updateUserInformation(user)
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "createUserWithEmail:failure", task.exception)
                        //Toast.makeText(this@FireBaseHelper, "Authentication failed.", Toast.LENGTH_SHORT).show()
                    }
                })

    }

    private fun verifyEmail() {

        val mUser = mAuth!!.currentUser;

        mUser!!.sendEmailVerification().addOnCompleteListener(OnCompleteListener { task ->
            if (task.isSuccessful) {
                //Toast.makeText(this@FireBaseHelper, "Verification email sent to " + mUser.getEmail(), Toast.LENGTH_SHORT).show()
            } else {
                Log.e(TAG, "sendEmailVerification", task.exception)
                //Toast.makeText(this@FireBaseHelper, "Failed to send verification email.", Toast.LENGTH_SHORT).show()
            }
        })
    }


    public fun loginUser(email: String, password: String) {

        mAuth!!.signInWithEmailAndPassword(email, password).addOnCompleteListener(OnCompleteListener {task ->

            if (task.isSuccessful) {
                // G0 to Main Activity
            } else {
                // If sign in fails, display a message to the user.
                Log.e(TAG, "signInWithEmail:failure", task.exception)
            }
        })

    }

    public fun updateUserObject(user: User) {
        val mUser = mAuth!!.currentUser
        val mUserRef = mDatabaseReference!!.child(mUser!!.uid)

        mUserRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val user = snapshot as User
            }
            override fun onCancelled(databaseError: DatabaseError) {}
        })
    }

    public fun updateUserDbInformation(user: User) {

        mUserDbRef!!.setValue(user).addOnCompleteListener(OnCompleteListener { task ->

            if (task.isSuccessful) {
                // Go to Profile Activity or Main Activity
            } else {
                Log.w(TAG, "updateUser:failure", task.exception)
            }
        })
    }

    public fun sendPasswordReset(user: User) {

        mAuth!!.sendPasswordResetEmail(user.email!!).addOnCompleteListener(OnCompleteListener { task ->

            if (task.isSuccessful) {
                // Go to Login Activity
            } else {
                Log.w(TAG, "updateUser:failure", task.exception)
            }
        })
    }

    public fun logoutUser() {
        mAuth!!.signOut()
    }

}

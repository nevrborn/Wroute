package com.donkeymonkey.wroute.helpers

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.donkeymonkey.wroute.models.User
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*

class FireBaseAuthHelper(private val context: Context): FirebaseBaseHelper() {

    private val TAG = "FirebaseAuthHelper"

    init {
        initialiseFirebase(context)
    }

    /* EMAIL AUTHENTICATION */

//    public fun createNewUserAccount(user: User, password: String) {
//
//        mAuth!!.createUserWithEmailAndPassword(user.email!!, password)
//                .addOnCompleteListener(OnCompleteListener { task ->
//
//                    if (task.isSuccessful) {
//                        Log.d(TAG, "createUserWithEmail:success")
//
//                        verifyEmail()
//
//                        user.uid = mAuth!!.currentUser!!.uid
//
//                        updateUserDbInformation(user)
//                    } else {
//                        // If sign in fails, display a message to the user.
//                        Log.w(TAG, "createUserWithEmail:failure", task.exception)
//                        //Toast.makeText(this@FireBaseAuthHelper, "Authentication failed.", Toast.LENGTH_SHORT).show()
//                    }
//                })
//
//    }
//
//    private fun verifyEmail() {
//
//        val mUser = mAuth!!.currentUser;
//
//        mUser!!.sendEmailVerification().addOnCompleteListener(OnCompleteListener { task ->
//            if (task.isSuccessful) {
//                //Toast.makeText(this@FireBaseAuthHelper, "Verification email sent to " + mUser.getEmail(), Toast.LENGTH_SHORT).show()
//            } else {
//                Log.e(TAG, "sendEmailVerification", task.exception)
//                //Toast.makeText(this@FireBaseAuthHelper, "Failed to send verification email.", Toast.LENGTH_SHORT).show()
//            }
//        })
//    }
//
//
//    public fun loginUser(email: String, password: String) {
//
//        mAuth!!.signInWithEmailAndPassword(email, password).addOnCompleteListener(OnCompleteListener {task ->
//
//            if (task.isSuccessful) {
//                // G0 to Main Activity
//            } else {
//                // If sign in fails, display a message to the user.
//                Log.e(TAG, "signInWithEmail:failure", task.exception)
//            }
//        })
//
//    }

    /* USER FUNCTIONS */

//    fun updateUserObject(user: User) {
//        val mUser = mAuth!!.currentUser
//        val mUserRef = mDatabaseReference!!.child(mUser!!.uid)
//
//        mUserRef.addValueEventListener(object : ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//                val user = snapshot as User
//            }
//            override fun onCancelled(databaseError: DatabaseError) {}
//        })
//    }
//
//    public fun updateUserDbInformation(user: User) {
//
//        mUserDbRef!!.setValue(user).addOnCompleteListener(OnCompleteListener { task ->
//
//            if (task.isSuccessful) {
//                // Go to Profile Activity or Main Activity
//            } else {
//                Log.w(TAG, "updateUser:failure", task.exception)
//            }
//        })
//    }

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

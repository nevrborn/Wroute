package com.donkeymonkey.wroute.models

import android.graphics.Bitmap
import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
class User {
    var uid: String? = null

    var firstName: String? = null
    var lastName: String? = null
    var email: String? = null
    var phone: String? = null

    var profileImage: Bitmap? = null
    var country: String? = null
    var town: String? = null

    var descripton: String? = null

    var wrouteIds: HashMap<String, Boolean>? = null
    var likedWrouteIds: HashMap<String, Boolean>? = null
    var completedWrouteIds: HashMap<String, Boolean>? = null

    var following: ArrayList<String>? = null
    var followers: ArrayList<String>? = null

    constructor() {

    }

    constructor(uid: String?, firstName: String?, lastName: String?, profileImage: Bitmap?) {
        this.uid = uid
        this.firstName = firstName
        this.lastName = lastName
        this.profileImage = profileImage
    }


}



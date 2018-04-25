package com.donkeymonkey.wroute.models

import android.graphics.Bitmap
import com.donkeymonkey.wroute.defines.Defines
import java.io.Serializable
import java.text.SimpleDateFormat
import com.google.android.gms.maps.model.PolylineOptions
import java.util.*
import kotlin.collections.ArrayList

class Wroute : Serializable, BaseModel {

    var uid: String? = null

    var creatorId: String? = null
    var createDate: Long? = null
    var createDateString: String? = null
        get() = getDate(createDate.let { it } ?: 0)

    var name: String? = null
    var description: String? = null
    val image: Bitmap? = null

    val transportMode: Defines.Transport? = null
    var category: String? = null
    var type: HashMap<Defines.WrouteType?, Boolean>? = null

    var stops = ArrayList<Stop>()
    var distance: Int? = null
    var duration: Int? = null

    var polyLine: PolylineOptions? = null

    var rating: Int? = null
    var numberOfWalksDone: Int? = null

    constructor() {

    }

    constructor(userId: String, name: String, description: String) {
        this.creatorId = userId
        this.name = name
        this.description = description
    }

    constructor(uid: String?, userId: String?, name: String?, description: String?, type: Defines.WrouteType?, distance: Int?, duration: Int?) : super() {
        this.uid = uid
        this.creatorId = userId
        this.createDate = Date().time
        this.name = name
        this.description = description
        //this.type = type
        this.distance = distance
        this.duration = duration

        setDateString()
    }

    fun setDateString() {
        createDateString = getDate(createDate.let { it } ?: 0)
    }



}


package com.donkeymonkey.wroute.models

import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class Campaign : BaseModel {

    var name: String? = null
    var description: String? = null
    var location: String? = null
    var startDate: Long? = null
    var endDate: Long? = null

    var wrouteIds: HashMap<String, Boolean>? = null

    constructor() {

    }

    constructor(name: String) : super() {
        this.name = name
    }



}

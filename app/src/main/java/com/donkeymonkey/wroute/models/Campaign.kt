package com.donkeymonkey.wroute.models

import java.util.*
import kotlin.collections.ArrayList

class Campaign : BaseModel {

    var name: String? = null
    var description: String? = null
    var location: String? = null
    var startDate: Long? = null
    var endDate: Long? = null

    var wroutes: ArrayList<Wroute>? = null

    constructor() {

    }

    constructor(name: String) : super() {
        this.name = name
    }



}

package com.donkeymonkey.wroute.models

import java.sql.Time
import java.util.Date


class Trip: BaseModel {

    var wrouteId: String? = null
    var dateAndTime: Long? = null
    var text: String? = null

    var creatorId: String? = null
    var participants: ArrayList<User>? = null

    constructor() {

    }

    constructor(wrouteId: String, dateAndTime: Date, text: String, creatorId: String) : super() {
        this.wrouteId = wrouteId
        this.dateAndTime = dateAndTime.time
        this.text = text
        this.creatorId = creatorId
    }



}
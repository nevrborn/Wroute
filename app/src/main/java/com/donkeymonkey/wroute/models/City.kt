package com.donkeymonkey.wroute.models

class City : BaseModel {

    var name: String? = null
    var country: String? = null
    var location: String? = null
    var wrouteIds: HashMap<String, Boolean>? = null

    constructor() {

    }

    constructor(name: String, country: String, location: String) : super() {
        this.name = name
        this.country = country
        this.location = location
    }

}
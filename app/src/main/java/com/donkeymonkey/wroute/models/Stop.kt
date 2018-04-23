package com.donkeymonkey.wroute.models

import android.graphics.Bitmap
import java.io.Serializable
import android.location.Location
import com.google.android.gms.maps.model.LatLng


class WayPoint : Serializable, BaseModel {

    var name: String? = null
    var address: String? = null
    var description: String? = null
    var coordinates: LatLng? = null
    var image: Bitmap? = null
    var shortStop: Boolean? = null

    constructor(name: String, address: String, description: String, coordinates: LatLng, isShortStop: Boolean?) {
        this.name = name
        this.address = address
        this.description = description
        this.coordinates = coordinates
        shortStop = isShortStop
    }

    constructor() {}

    constructor(address: String, coordinates: LatLng, isShortStop: Boolean?) {
        this.address = address
        this.coordinates = coordinates
        shortStop = isShortStop
    }

    constructor(coordinates: LatLng) {
        this.coordinates = coordinates
    }
}

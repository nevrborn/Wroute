package com.donkeymonkey.wroute.models

import android.graphics.Bitmap
import java.io.Serializable
import java.text.SimpleDateFormat
import java.util.ArrayList

class Wroute : Serializable, BaseModel {

    var wrouteID: String? = null
    var userID: String? = null
    var date: Long = 0
    var wrouteName: String? = null
    var wrouteShortDescription: String? = null
    var wrouteLongDescription: String? = null
    var wrouteImage: Bitmap? = null
    var category: String? = null
    var polyLine: PolylineOptions? = null
    var wayPoints = ArrayList<WayPoint>()
    var distance: Int = 0
    var duration: Int = 0
    //private ArrayList<LatLng> mCoordinateList = new ArrayList<>();

    constructor() {

    }

    constructor(wrouteID: String, userID: String, wrouteName: String, wrouteDescription: String) {
        this.wrouteID = wrouteID
        this.userID = userID
        this.wrouteName = wrouteName
        wrouteShortDescription = wrouteDescription
    }

    fun getDateAndTime(date: Long): String {
        val tempDate = date * 1000L
        val dateFormat = SimpleDateFormat("dd-MM-yyyy HH:mm")
        return dateFormat.format(tempDate)
    }

    //    public ArrayList<LatLng> getCoordinateList() {
    //        for (int i = 0; i < getWayPoints().size(); i++) {
    //            mCoordinateList.add(mWayPoints.get(i).getCoordinates());
    //        }
    //        return mCoordinateList;
    //    }

    fun addWayPoint(waypoint: WayPoint) {
        wayPoints.add(waypoint)
    }
}


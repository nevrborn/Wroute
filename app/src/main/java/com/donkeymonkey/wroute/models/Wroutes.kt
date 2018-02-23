package com.donkeymonkey.wroute.models

import java.util.ArrayList

class Wroutes : BaseModel() {

    var wroutes: ArrayList<Wroute>? = null

    fun addWroute(wroute: Wroute) {
        wroutes!!.add(wroute)
    }

    fun removeWroute(wroute: Wroute) {
        val id = wroute.getWrouteID()
        for (i in wroutes!!.indices) {
            if (wroutes!![i].getWrouteID().equals(wroute.getWrouteID())) {
                wroutes!!.removeAt(i)
            }
        }
    }
}

package com.donkeymonkey.wroute.models

import com.donkeymonkey.wroute.defines.Defines
import java.net.URL

class StopExtraInfo {

    var stop: Stop? = null

    var category: HashMap<Defines.StopCategory, Boolean>? = null
    var eatingOptions: HashMap<Defines.StopEating, Boolean>? = null
    var cost: HashMap<Defines.StopCost, Boolean>? = null

    var openingHourWeek: Int? = null
    var closingHourWeek: Int? = null
    var openingHourWeekend: Int? = null
    var closingHourWeekend: Int? = null

    var website: URL? = null


    constructor(stop: Stop) {
        this.stop = stop
    }

}
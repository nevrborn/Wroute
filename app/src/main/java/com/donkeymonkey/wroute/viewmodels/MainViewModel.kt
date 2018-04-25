package com.donkeymonkey.wroute.viewmodels

import android.arch.lifecycle.MutableLiveData
import com.donkeymonkey.wroute.WrouteApplication
import com.donkeymonkey.wroute.defines.Defines
import com.donkeymonkey.wroute.models.Campaign
import com.donkeymonkey.wroute.models.City
import com.donkeymonkey.wroute.models.Trip
import com.donkeymonkey.wroute.models.Wroute
import io.reactivex.Observable
import java.time.Instant
import java.util.*

class MainViewModel : BaseViewModel() {

    var cities: MutableLiveData<List<City>>? = null
    var city: MutableLiveData<City>? = null
    var wroutes: MutableLiveData<List<Wroute>>? = null
    var trips: MutableLiveData<List<Trip>>? = null
    var campaigns: MutableLiveData<List<Campaign>>? = null

    var wroute1 = Wroute("1", "MkJmwUTZDlUQP7Iqw770PqIVDkb2",  "TEST WROUTE", "Just a little test", Defines.WrouteType.Curated, 10, 23)
    var wroute2 = Wroute("2", "MkJmwUTZDlUQP7Iqw770PqIVDkb2", "WITH A VIEW", "VERY nice wroute", Defines.WrouteType.User, 13, 3)

    var items: ArrayList<String> = ArrayList()

    init {
        items.add("Amsterdam")
        items.add("Tokyo")
        items.add("New York")
    }

    companion object {
        val TAG: String = "OutBoxViewModel"
    }




}

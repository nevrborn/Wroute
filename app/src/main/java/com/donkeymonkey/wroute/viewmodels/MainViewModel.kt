package com.donkeymonkey.wroute.viewmodels

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.donkeymonkey.wroute.defines.Defines
import com.donkeymonkey.wroute.models.Campaign
import com.donkeymonkey.wroute.models.City
import com.donkeymonkey.wroute.models.Trip
import com.donkeymonkey.wroute.models.Wroute
import java.util.*

class MainViewModel : BaseViewModel() {

    var cities: MutableLiveData<List<City>>
    var city: MutableLiveData<City>
    var wroutes: MutableLiveData<List<Wroute>>
    var trips: MutableLiveData<List<Trip>>
    var campaigns: MutableLiveData<List<Campaign>>

    var wroute1 = Wroute("2", "MkJmwUTZDlUQP7Iqw770PqIVDkb2", "C1443p2Kdbpm0tLzDQSa","TAMSTERDAM", "Just a little test", Defines.WrouteType.Curated, 22, 33)
    var wroute2 = Wroute("3", "MkJmwUTZDlUQP7Iqw770PqIVDkb2", "C1443p2Kdbpm0tLzDQSa", "TSTREET ART", "BLABLA", Defines.WrouteType.Curated, 3, 7)
    var city1 = City("Amsterdam", "The Netherlands", "Europe")
    var city2 = City("Rotterdam", "The Netherlands", "Europe")

    var trip1: Trip? = null
    var trip2: Trip? = null
    var trip3: Trip? = null

    var calendar = Calendar.getInstance()


    var items: ArrayList<String> = ArrayList()

    init {
        cities = MutableLiveData<List<City>>()
        city = MutableLiveData<City>()
        wroutes = MutableLiveData<List<Wroute>>()
        trips = MutableLiveData<List<Trip>>()
        campaigns = MutableLiveData<List<Campaign>>()

        items.add("Amsterdam")
        items.add("Tokyo")
        items.add("New York")

        var date1 = calendar.add(Calendar.DATE, 1)
        var date2 = calendar.add(Calendar.DATE, 2)

        trip1 = Trip("G3VB0bGGSe0hfZcrWIAf", "C1443p2Kdbpm0tLzDQSa", Date(), "Blbablabla", "MkJmwUTZDlUQP7Iqw770PqIVDkb2")
        trip2 = Trip("xXrY9Ish7uj8UgtSBMNm", "C1443p2Kdbpm0tLzDQSa", Date(), "This trip sis gonna be awesome", "MkJmwUTZDlUQP7Iqw770PqIVDkb2")
        trip3 = Trip("6yMjmNBxZiYDx5dGnUKV", "C1443p2Kdbpm0tLzDQSa", Date(), "Another test trip", "MkJmwUTZDlUQP7Iqw770PqIVDkb2")

    }

    companion object {
        val TAG: String = "OutBoxViewModel"
    }

    fun getWroutesLive(): LiveData<List<Wroute>> {
        return wroutes
    }

    fun setWroutes(list: List<Wroute>) {
        wroutes.postValue(list)
    }

}

package com.donkeymonkey.wroute.viewmodels

import android.arch.lifecycle.LiveData
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

    lateinit var cities: MutableLiveData<List<City>>
    lateinit var city: MutableLiveData<City>
    lateinit var wroutes: MutableLiveData<List<Wroute>>
    lateinit var trips: MutableLiveData<List<Trip>>
    lateinit var campaigns: MutableLiveData<List<Campaign>>

    var wroute1 = Wroute("2", "MkJmwUTZDlUQP7Iqw770PqIVDkb2",  "TAMSTERDAM", "Just a little test", Defines.WrouteType.Curated, 22, 33)
    var wroute2 = Wroute("3", "MkJmwUTZDlUQP7Iqw770PqIVDkb2",  "TSTREET ART", "BLABLA", Defines.WrouteType.Curated, 3, 7)
    var city1 = City("Amsterdam", "The Netherlands", "Europe")
    var city2 = City("Rotterdam", "The Netherlands", "Europe")
    var trip1 = Trip("G3VB0bGGSe0hfZcrWIAf", Date(), "Blbablabla", "MkJmwUTZDlUQP7Iqw770PqIVDkb2")

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

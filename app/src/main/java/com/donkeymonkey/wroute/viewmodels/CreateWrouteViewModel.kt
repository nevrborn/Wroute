package com.donkeymonkey.wroute.viewmodels

import android.arch.lifecycle.MutableLiveData
import android.databinding.ObservableField
import com.donkeymonkey.wroute.defines.Defines
import com.donkeymonkey.wroute.models.Wroute

class CreateWrouteViewModel: BaseViewModel() {

    var wroute = MutableLiveData<Wroute>()
    var wroute1 = Wroute("1", "MkJmwUTZDlUQP7Iqw770PqIVDkb2",  "TEST WROUTE", "Just a little test", Defines.WrouteType.Curated, 10, 23)

    init {
        this.wroute.postValue(wroute1)
    }

    val description = ObservableField<String>()

    fun getWrouteLive() = wroute
}
package com.donkeymonkey.wroute.viewmodels

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.database.Observable
import android.databinding.ObservableField
import com.donkeymonkey.wroute.defines.Defines
import com.donkeymonkey.wroute.models.Wroute
import io.reactivex.internal.operators.observable.ObservableAny

class CreateWrouteViewModel: BaseViewModel() {

    var wroute = MutableLiveData<Wroute>()


    init {
        wroute

        wroute.postValue(Wroute())
    }

    val description = ObservableField<String>()

    fun getWroute(): LiveData<Wroute> {
        return wroute
    }
}
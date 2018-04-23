package com.donkeymonkey.wroute.viewmodels

import android.arch.lifecycle.MutableLiveData
import com.donkeymonkey.wroute.WrouteApplication
import com.donkeymonkey.wroute.defines.Defines
import com.donkeymonkey.wroute.models.Wroute
import io.reactivex.Observable
import java.time.Instant
import java.util.*

class MainViewModel : BaseViewModel() {

    var wroute1 = Wroute("1", "MkJmwUTZDlUQP7Iqw770PqIVDkb2",  "TEST WROUTE", "Just a little test", Defines.WrouteType.Curated, 10, 23)
    var wroute2 = Wroute("2", "MkJmwUTZDlUQP7Iqw770PqIVDkb2", "WITH A VIEW", "VERY nice wroute", Defines.WrouteType.Popular, 13, 3)

    var wroutes: Observable<List<Wroute>>? = null

    init {

    }

    companion object {
        val TAG: String = "OutBoxViewModel"
    }




}

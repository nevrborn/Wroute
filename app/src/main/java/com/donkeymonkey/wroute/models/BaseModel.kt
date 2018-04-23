package com.donkeymonkey.wroute.models

import android.databinding.BaseObservable
import java.text.SimpleDateFormat
import java.util.*


abstract class  BaseModel : BaseObservable() {

    fun getDateAndTime(date: Long): String {
        val tempDate = date * 1000L
        val dateFormat = SimpleDateFormat("dd-MM-yyyy HH:mm", Locale.getDefault())
        return dateFormat.format(tempDate)
    }

    fun getDate(date: Long): String {
        val tempDate = date * 1000L
        val dateFormat = SimpleDateFormat("dd-MM", Locale.getDefault())
        return dateFormat.format(tempDate)
    }

    fun getTime(date: Long): String {
        val tempDate = date * 1000L
        val dateFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
        return dateFormat.format(tempDate)
    }
}

package com.donkeymonkey.wroute.helpers

import android.content.Context
import android.widget.Toast

class InteractionHelper(private val context: Context) {
    public fun toast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    fun toast(resource: Int) {
        this.toast(context.getString(resource))
    }
}
package co.woost.napkinnote.helpers

import android.content.Context
import android.widget.Toast

/**
 * Created by Sebastiaan on 21-11-2017.
 */

class InteractionHelper(private val context: Context) {
    fun toast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    fun toast(resource: Int) {
        this.toast(context.getString(resource))
    }
}
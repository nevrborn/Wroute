package com.donkeymonkey.wroute.views.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.DialogInterface
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import com.donkeymonkey.wroute.R
import com.donkeymonkey.wroute.databinding.DialogAddStopBinding
import com.donkeymonkey.wroute.viewmodels.StopViewModel

class StopDialog: DialogFragment() {
    lateinit var viewModel: StopViewModel
    lateinit var binding: DialogAddStopBinding
    lateinit var listener: OnDialogInteractionListener

    internal var mOnDismissListener: DialogInterface.OnDismissListener? = null

    fun newInstance(): DialogFragment {
        return StopDialog()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        viewModel = ViewModelProviders.of(activity!!).get(StopViewModel::class.java)
        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.dialog_add_stop, null, false)

        binding.viewModel = viewModel

        return AlertDialog.Builder(activity)
                .setView(view)
                .create()
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnDialogInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnDialogInteractionListener")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE)
    }

    interface OnDialogInteractionListener {

    }

    companion object {
        fun newInstance(): StopDialog {
            val dialog = StopDialog()
            return dialog
        }
    }

    fun setOnDismissListener(onDismissListener: DialogInterface.OnDismissListener) {
        mOnDismissListener = onDismissListener
    }

    override fun onDismiss(dialog: DialogInterface?) {
        if (mOnDismissListener != null) {
//            mOnDismissListener.onDismiss(this.dialog)
        }

        super.onDismiss(dialog)
    }
}
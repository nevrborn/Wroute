package com.donkeymonkey.wroute.views.dialogs

import android.app.Dialog
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.DialogInterface
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import com.donkeymonkey.wroute.R
import com.donkeymonkey.wroute.databinding.DialogAddStopBinding
import com.donkeymonkey.wroute.viewmodels.StopViewModel

class StopDialog : DialogFragment() {
    lateinit var viewModel: StopViewModel
    lateinit var binding: DialogAddStopBinding
    lateinit var listener: OnDialogInteractionListener

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        viewModel = ViewModelProviders.of(activity!!).get(StopViewModel::class.java)
        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.dialog_add_stop, null, false)
        binding.viewModel = viewModel


        return dialog
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
        fun onContactSaveClicked()
        fun onContactCancelClicked()
        fun onContactDeleteClicked()
    }

    companion object {
        fun newInstance(): StopDialog {
            val dialog = StopDialog()
            return dialog
        }
    }
}
package com.donkeymonkey.wroute.views.activities

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import com.donkeymonkey.wroute.R
import com.donkeymonkey.wroute.databinding.ActivityAgendaBinding
import com.donkeymonkey.wroute.viewmodels.AgendaViewModel
import java.util.*
import devs.mulham.horizontalcalendar.HorizontalCalendar
import devs.mulham.horizontalcalendar.utils.HorizontalCalendarListener





class AgendaActivity: BaseActivity() {
    lateinit var viewModel: AgendaViewModel
    lateinit var binding: ActivityAgendaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(AgendaViewModel::class.java)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_agenda)
        binding.activity = this
        binding.viewModel = viewModel

        /* starts before 1 month from now */
        var startDate = Calendar.getInstance()
        startDate.add(Calendar.MONTH, -1)

        /* ends after 1 month from now */
        var endDate = Calendar.getInstance()
        endDate.add(Calendar.MONTH, 1)


        val horizontalCalendar = HorizontalCalendar.Builder(this, R.id.calendarView)
                .range(startDate, endDate)
                .datesNumberOnScreen(5)
                .build()

    }


}
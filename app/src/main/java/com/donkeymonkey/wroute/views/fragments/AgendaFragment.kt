package com.donkeymonkey.wroute.views.fragments

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.donkeymonkey.wroute.R
import com.donkeymonkey.wroute.databinding.FragmentAgendaBinding
import com.donkeymonkey.wroute.models.Trip
import com.donkeymonkey.wroute.models.Wroute
import com.donkeymonkey.wroute.viewmodels.MainViewModel
import com.donkeymonkey.wroute.views.adapters.GenericRecyclerViewAdapter
import devs.mulham.horizontalcalendar.HorizontalCalendar
import devs.mulham.horizontalcalendar.utils.HorizontalCalendarListener
import java.util.*

class AgendaFragment: BaseFragment() {

    lateinit var viewModel: MainViewModel
    lateinit var binding: FragmentAgendaBinding

    fun newInstance(): Fragment {
        return AgendaFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_agenda, container, false)

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        setUpHelpers(this.context!!)

        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_agenda, container, true)
        binding.viewModel = viewModel
        binding.fragment = this
        binding.setLifecycleOwner(this)

        /* starts before 1 month from now */
        val startDate = Calendar.getInstance()

        /* ends after 1 month from now */
        val endDate = Calendar.getInstance()
        endDate.add(Calendar.MONTH, 1)

        val horizontalCalendar = HorizontalCalendar.Builder(view.rootView, R.id.calendarView)
                .range(startDate, endDate)
                .datesNumberOnScreen(5)
                .build()


        var horizontalListener = HorizontalListener()

        horizontalCalendar.calendarListener = horizontalListener

        setupTripsTable()

        return view
    }

    fun setupTripsTable() {
        val tripsAdapter = GenericRecyclerViewAdapter(R.layout.list_item_wroute_agenda_large, object: GenericRecyclerViewAdapter.InteractionListener<Trip> {
            override fun onItemSelected(item: Trip?) {

            }
        })

        binding.mainListTrips.adapter = tripsAdapter

        firebaseDBHelper.getCityTrips(prefsHelper.currentCityId).subscribe { trips ->
            viewModel.trips.postValue(trips)

            tripsAdapter.replaceItems(trips)
            tripsAdapter.notifyDataSetChanged()
        }
    }

}


class HorizontalListener: HorizontalCalendarListener() {
    override fun onDateSelected(date: Calendar?, position: Int) {

    }

}
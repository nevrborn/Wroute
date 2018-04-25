package com.donkeymonkey.wroute.views.fragments

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.donkeymonkey.wroute.R
import com.donkeymonkey.wroute.databinding.FragmentCreateMainBinding
import com.donkeymonkey.wroute.databinding.FragmentMainBinding
import com.donkeymonkey.wroute.models.City
import com.donkeymonkey.wroute.models.User
import com.donkeymonkey.wroute.models.Wroute
import com.donkeymonkey.wroute.viewmodels.MainViewModel
import com.donkeymonkey.wroute.views.adapters.GenericRecyclerViewAdapter
import io.reactivex.Observable

class MainFragment: BaseFragment() {

    lateinit var viewModel: MainViewModel
    lateinit var binding: FragmentMainBinding

    var user: User = User()

    fun newInstance(): Fragment {
        return MainFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        setUpHelpers(this.context!!)

        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_main, container, false)
        binding.fragment = this
        binding.viewModel = viewModel

        firebaseDBHelper.updateRefs(prefsHelper.currentCityId)

        sendDummyWroutes()
        setupAgendaCarousel()
        setupRoutesTable()

        return binding.root
    }

    fun sendDummyWroutes() {
        firebaseDBHelper.storeCity(City("Amsterdam", "The Netherlands", "Blabla")).subscribe()
        firebaseDBHelper.storeWroute(viewModel.wroute1).subscribe()
        firebaseDBHelper.storeWroute(viewModel.wroute2).subscribe()
    }

    fun setupAgendaCarousel() {
        val agendaAdapter = GenericRecyclerViewAdapter<Wroute>(R.layout.list_item_wroute_agenda, object: GenericRecyclerViewAdapter.InteractionListener<Wroute> {
            override fun onItemSelected(item: Wroute?) {

            }
        })

        binding.mainRecyclerViewAgenda.adapter = agendaAdapter

        firebaseDBHelper.getMeProfileFromFirebase().subscribe { user ->
            this.user = user
            firebaseDBHelper.getCityWroutes(prefsHelper.currentCityId).subscribe { wroutes ->
                viewModel.wroutes?.postValue(wroutes)

                agendaAdapter.replaceItems(wroutes)
                agendaAdapter.notifyDataSetChanged()
            }
        }
    }

    fun setupRoutesTable() {
        val wrouteAdapter = GenericRecyclerViewAdapter<Wroute>(R.layout.list_item_wroute, object: GenericRecyclerViewAdapter.InteractionListener<Wroute> {
            override fun onItemSelected(item: Wroute?) {

            }
        })

        binding.mainRecyclerViewWroutes.adapter = wrouteAdapter

        firebaseDBHelper.getMeProfileFromFirebase().subscribe { user ->
            this.user = user
            firebaseDBHelper.getCityWroutes(prefsHelper.currentCityId).subscribe { wroutes ->
                viewModel.wroutes?.postValue(wroutes)

                wrouteAdapter.replaceItems(wroutes)
                wrouteAdapter.notifyDataSetChanged()
            }
        }
    }

}
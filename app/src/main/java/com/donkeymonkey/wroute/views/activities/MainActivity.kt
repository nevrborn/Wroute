package com.donkeymonkey.wroute.views.activities

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.donkeymonkey.wroute.R
import com.donkeymonkey.wroute.databinding.ActivityMainBinding
import com.donkeymonkey.wroute.models.City
import com.donkeymonkey.wroute.models.User
import com.donkeymonkey.wroute.models.Wroute
import com.donkeymonkey.wroute.viewmodels.MainViewModel
import com.donkeymonkey.wroute.views.adapters.GenericRecyclerViewAdapter
import io.reactivex.Observable
import android.support.v7.widget.RecyclerView



class MainActivity : BaseActivity() {

    lateinit var viewModel: MainViewModel
    lateinit var binding: ActivityMainBinding

    var user: User = User()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.activity = this
        binding.viewModel = viewModel

        setSupportActionBar(findViewById(R.id.toolbar))

        firebaseDBHelper.updateRefs(prefsHelper.currentCityId)

        sendDummyWroutes()

        setupAgendaCarousel()
        setupRoutesTable()

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

        binding.mainListWroutesAgenda.adapter = agendaAdapter

        firebaseDBHelper.getMeProfileFromFirebase().subscribe { user ->
            this.user = user
            firebaseDBHelper.getCityWroutes(prefsHelper.currentCityId).subscribe { wroutes ->
                viewModel.wroutes = Observable.just(wroutes)

                viewModel.wroutes?.subscribe( { it ->
                    agendaAdapter.replaceItems(it!!)
                    agendaAdapter.notifyDataSetChanged()
                })
            }
        }

    }

    fun setupRoutesTable() {
        val wrouteAdapter = GenericRecyclerViewAdapter<Wroute>(R.layout.list_item_wroute, object: GenericRecyclerViewAdapter.InteractionListener<Wroute> {
            override fun onItemSelected(item: Wroute?) {

            }
        })

        binding.mainListWroutes.adapter = wrouteAdapter

        firebaseDBHelper.getMeProfileFromFirebase().subscribe { user ->
            this.user = user
            firebaseDBHelper.getCityWroutes(prefsHelper.currentCityId).subscribe { wroutes ->
                viewModel.wroutes = Observable.just(wroutes)

                viewModel.wroutes?.subscribe( { it ->
                    wrouteAdapter.replaceItems(it!!)
                    wrouteAdapter.notifyDataSetChanged()
                })
            }
        }
    }

}

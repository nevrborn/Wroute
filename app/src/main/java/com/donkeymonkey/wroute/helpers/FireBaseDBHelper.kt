package com.donkeymonkey.wroute.helpers

import android.content.Context
import com.donkeymonkey.wroute.defines.Defines
import com.donkeymonkey.wroute.models.*
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import de.aaronoe.rxfirestore.getCompletable
import de.aaronoe.rxfirestore.getObservable
import de.aaronoe.rxfirestore.getSingle
import de.aaronoe.rxfirestore.setDocument
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single


class FireBaseDBHelper(private val context: Context): FirebaseBaseHelper() {

    private val userCollectionReference = FirebaseFirestore.getInstance().collection(Defines.FirebaseDB.Users.databaseRef)
    private val wrouteCollectionReference = FirebaseFirestore.getInstance().collection(Defines.FirebaseDB.Wroutes.databaseRef)
    private val citiesCollectionReference = FirebaseFirestore.getInstance().collection(Defines.FirebaseDB.Cities.databaseRef)
    private val tripCollectionReference = FirebaseFirestore.getInstance().collection(Defines.FirebaseDB.Trips.databaseRef)
    private var campaignCollectionReference = FirebaseFirestore.getInstance().collection(Defines.FirebaseDB.Campaigns.databaseRef)

    private var cityDocumentReference: DocumentReference? = null


    init {
        initialiseFirebase(context)
    }

    fun updateRefs(cityId: String) {
        cityDocumentReference = citiesCollectionReference.document(cityId)
    }

    private fun getCollectionReference(type: Defines.FirebaseDB): CollectionReference? {

        when (type) {
            Defines.FirebaseDB.Users -> return userCollectionReference
            Defines.FirebaseDB.Wroutes -> return wrouteCollectionReference
            Defines.FirebaseDB.Cities -> return citiesCollectionReference
            Defines.FirebaseDB.Trips -> return tripCollectionReference
            Defines.FirebaseDB.Campaigns -> return campaignCollectionReference
            else -> {
                return null
            }
        }
    }

    // GENERAL

    fun getCollection(type: Defines.FirebaseDB) {

    }

    fun getSingleDocument(type: Defines.FirebaseDB, id: String): Single<Any> {
        val reference = getCollectionReference(type)?.document(id).let { it } ?: return Single.never()
        return reference.getSingle()
    }

    fun getDocuments(type: Defines.FirebaseDB, id: String): Observable<List<Any>> {
        val reference = getCollectionReference(type)?.document(id).let { it } ?: return Observable.never()
        return reference.getObservable()
    }

    fun storeDocument(type: Defines.FirebaseDB, document: Any): Completable {

        val reference = getCollectionReference(type)

        return reference?.add(document)?.getCompletable()!!
    }

    // USER

    fun storeProfile(user: User): Completable {

        val userId = user.uid.let { it } ?: return Completable.never()

        val userDocumentReference = userCollectionReference.document(userId)
        return userDocumentReference.setDocument(user)

    }

    fun getMeProfileFromFirebase(): Single<User> {

        val userId = PrefsHelper(context).userId.let { it }

        val userDocumentReference = userCollectionReference.document(userId)
        return userDocumentReference.getSingle()

    }

    fun getUserProfile(uid: String): Single<User> {
        val userDocumentReference = userCollectionReference.document(uid)
        return userDocumentReference.getSingle()
    }

    fun getUsersWroutes(userId: String): Observable<List<Wroute>> {

        val query = wrouteCollectionReference.whereEqualTo("uid", userId)
        return query.getObservable()
    }

    // WROUTES

    fun storeWroute(wroute: Wroute): Completable {
        val wrouteId = wroute.uid.let { it } ?: return Completable.never()

        val wrouteDocumentReference = wrouteCollectionReference.document(wrouteId)
        return wrouteDocumentReference.setDocument(wroute)
    }

    fun getWroute(wrouteId: String): Single<Wroute> {

        val wrouteDocumentReference = wrouteCollectionReference.document(wrouteId)
        return wrouteDocumentReference.getSingle()
    }

    fun getCityWroutes(cityId: String): Observable<List<Wroute>> {

        val query = wrouteCollectionReference.whereEqualTo("cityId", cityId)
        return query.getObservable()
    }

    fun getCityTrips(cityId: String): Observable<List<Trip>> {

        val query = tripCollectionReference.whereEqualTo("cityId", cityId)
        return query.getObservable()
    }

    fun getCityCampaigns(cityId: String): Observable<List<Campaign>> {

        val query = tripCollectionReference.whereEqualTo("cityId", cityId)
        return query.getObservable()
    }


    // CITY

    fun storeCity(city: City): Completable {
        val cityName = city.name.let { it } ?: return Completable.never()

        val cityDocumentReference = citiesCollectionReference.document(cityName)
        return cityDocumentReference.setDocument(city)
    }

    fun getCity(cityId: String): Single<City> {

        val cityDocumentReference = citiesCollectionReference.document(cityId)
        return cityDocumentReference.getSingle()
    }

    // TRIP

    fun storeTrip(trip: Trip): Completable {
        val reference = tripCollectionReference
        return reference.add(trip).getCompletable()
    }

    fun getTrip(tripId: String): Single<Trip> {

        val cityDocumentReference = citiesCollectionReference.document(tripId)
        return cityDocumentReference.getSingle()
    }



}
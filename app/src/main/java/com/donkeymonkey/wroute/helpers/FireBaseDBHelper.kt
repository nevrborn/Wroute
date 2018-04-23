package com.donkeymonkey.wroute.helpers

import android.content.Context
import com.donkeymonkey.wroute.defines.Defines
import com.donkeymonkey.wroute.models.City
import com.donkeymonkey.wroute.models.User
import com.donkeymonkey.wroute.models.Wroute
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FieldPath
import com.google.firebase.firestore.FirebaseFirestore
import de.aaronoe.rxfirestore.getObservable
import de.aaronoe.rxfirestore.getSingle
import de.aaronoe.rxfirestore.setDocument
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single


class FireBaseDBHelper(private val context: Context): FirebaseBaseHelper() {

    val userCollectionReference = FirebaseFirestore.getInstance().collection(Defines.FirebaseDB.Users.databaseRef)
    val cityCollectionReference = FirebaseFirestore.getInstance().collection(Defines.FirebaseDB.City.databaseRef)
    var cityDocumentReference: DocumentReference? = null
    var wrouteCollectionReference: CollectionReference? = null
    var tripsCollectionReference: CollectionReference? = null
    var campaignCollectionReference: CollectionReference? = null

    init {
        initialiseFirebase(context)
    }

    fun updateRefs(cityId: String) {
        cityDocumentReference = FirebaseFirestore.getInstance().collection(Defines.FirebaseDB.City.databaseRef).document(cityId)
        wrouteCollectionReference = cityDocumentReference?.collection(Defines.FirebaseDB.Wroutes.databaseRef)
        tripsCollectionReference = cityDocumentReference?.collection(Defines.FirebaseDB.Trips.databaseRef)
        campaignCollectionReference = cityDocumentReference?.collection(Defines.FirebaseDB.Campaigns.databaseRef)
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
        return userDocumentReference.getSingle<User>()

    }

    fun getUserProfile(uid: String): Single<User> {
        val userDocumentReference = userCollectionReference.document(uid)
        return userDocumentReference.getSingle<User>()
    }

    fun getUsersWroutes(userId: String): Observable<List<Wroute>> {

        val wrouteQuery = wrouteCollectionReference?.whereEqualTo("uid", userId).let { it } ?: return Observable.never()
        return wrouteQuery.getObservable<Wroute>()
    }

    // WROUTES

    fun storeWroute(wroute: Wroute): Completable {
        val wrouteId = wroute.uid.let { it } ?: return Completable.never()

        val wrouteDocumentReference = wrouteCollectionReference?.document(wrouteId).let { it } ?: return Completable.never()
        return wrouteDocumentReference.setDocument(wroute)
    }

    fun getWroute(wrouteId: String): Single<Wroute> {

        val wrouteDocumentReference = wrouteCollectionReference?.document(wrouteId).let { it } ?: return Single.never()
        return wrouteDocumentReference.getSingle<Wroute>()
    }

    fun getCityWroutes(cityId: String): Observable<List<Wroute>> {

        val wrouteDocumentReference = wrouteCollectionReference.let { it } ?: return Observable.never()
        return wrouteDocumentReference.getObservable<Wroute>()
    }



    // CITY

    fun storeCity(city: City): Completable {
        val cityName = city.name.let { it } ?: return Completable.never()

        val cityDocumentReference = cityCollectionReference.document(cityName)
        return cityDocumentReference.setDocument(city)
    }

    fun getCity(cityId: String): Single<City> {

        val cityDocumentReference = wrouteCollectionReference?.document(cityId).let { it } ?: return Single.never()
        return cityDocumentReference.getSingle<City>()
    }



}
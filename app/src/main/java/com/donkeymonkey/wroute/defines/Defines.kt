package com.donkeymonkey.wroute.defines

class Defines {

    /* Firebase Paths */
    enum class FirebaseDB(val databaseRef: String) {
        Users("users"),
        Cities("cities"),
        Wroutes("wroutes"),
        City("city"),
        Trips("trips"),
        Campaigns("campaigns")
    }

    /* WROUTE ENUMS */

    enum class WrouteType {
        Curated,
        Campaign,
        User,
        Popular
    }
}
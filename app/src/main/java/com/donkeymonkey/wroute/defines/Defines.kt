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
        User
    }

    enum class Transport(val mode: String) {
        Walking("Walking"),
        Bicyle("Bicycling"),
        Driving("Driving"),
        Transit("Transit")
    }

    /* FILTER ENUMS */

    enum class FilterType {
        All,
        Curated,
        Hot,
        UserMade,
        FriendMade
    }

    enum class FilterSort {
        NoSort,
        HighRated,
        Created,
        ShortestTime,
        LongestTime,
        Closest
    }

    /* STOP ENUMS */
    enum class StopCategory {
        Museum,
        Shop
    }

    enum class StopEating {
        Breakfast,
        Lunch,
        Dinner,
        Coffee,
        Bar,
        Nightclub
    }

    enum class StopCost {
        Free,
        Cheap,
        Moderate,
        Expensive,
        VeryExpensive
    }


}
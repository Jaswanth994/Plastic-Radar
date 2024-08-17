package com.example.plastic_radar.data

data class UserData (
    val firstname: String,
    val lastname: String,
    val phonenumber: String,
    val locations: List<location>,
    val orders : List<order>
    )

data class location(
    val Country: String,
    val State: String,
    val District: String,
)

data class order(
    val typeofplastic : String,
    val quantity : Int,
    val price : Int,
)
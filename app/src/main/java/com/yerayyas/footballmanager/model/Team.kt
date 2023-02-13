package com.yerayyas.footballmanager.model

import com.google.gson.annotations.SerializedName

data class Team(
    @SerializedName("country")
    val country: String,
    @SerializedName("icon")
    val icon: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("players")
    var players: List<Player>,
    @SerializedName("Seats")
    val seats: Int,
    @SerializedName("stadium")
    val stadium: String
)
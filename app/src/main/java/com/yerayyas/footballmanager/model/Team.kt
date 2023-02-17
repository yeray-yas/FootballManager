package com.yerayyas.footballmanager.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
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
):Parcelable
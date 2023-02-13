package com.yerayyas.footballmanager.model

import com.google.gson.annotations.SerializedName

data class Player(
    @SerializedName("birthday")
    val birthday: String,
    @SerializedName("height")
    val height: Int,
    @SerializedName("lastname")
    val lastname: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("number")
    val number: Int,
    @SerializedName("photo")
    val photo: String,
    @SerializedName("position")
    val position: String,
    @SerializedName("rightFeet")
    val rightFeet: Boolean,
    @SerializedName("weight")
    val weight: Int
)


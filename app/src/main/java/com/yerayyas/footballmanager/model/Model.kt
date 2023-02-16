package com.yerayyas.footballmanager.model

import com.google.gson.annotations.SerializedName

data class Model(
    @SerializedName("team")
    val team: Team
) {

}
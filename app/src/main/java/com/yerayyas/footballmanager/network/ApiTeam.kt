package com.yerayyas.footballmanager.network

import com.yerayyas.footballmanager.model.Model
import retrofit2.Call
import retrofit2.http.GET

interface ApiTeam {

    @GET("teams.json")
    fun getData(): Call<Model>
}
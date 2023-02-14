package com.yerayyas.footballmanager.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object PlayerClient {

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://squaads.s3.amazonaws.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiInterface by lazy{
        retrofit.create(PlayerService::class.java)
    }
}
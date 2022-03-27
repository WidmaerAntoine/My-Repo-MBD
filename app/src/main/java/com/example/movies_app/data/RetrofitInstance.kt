package com.example.movies_app.data

import com.example.movies_app.presentation.utils.Constants.Companion.BASE_URL

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()

    }
    val myApi :ApiCall by lazy {
        retrofit.create(ApiCall::class.java)
    }
}
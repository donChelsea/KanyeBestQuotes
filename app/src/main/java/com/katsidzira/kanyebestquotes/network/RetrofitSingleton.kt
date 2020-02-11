package com.katsidzira.kanyebestquotes.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitSingleton {
    private var instance: Retrofit? = null
    private val BASE_URL: String = ""

    fun getInstance(): Retrofit? {
        if (instance == null) {
            instance = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return instance
    }
}
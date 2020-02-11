package com.katsidzira.kanyebestquotes.network

import com.katsidzira.kanyebestquotes.Quote
import retrofit2.Call
import retrofit2.http.GET

interface QuoteService {

    @GET("")
    fun getKanyeQuotes(): Call<Quote>

}
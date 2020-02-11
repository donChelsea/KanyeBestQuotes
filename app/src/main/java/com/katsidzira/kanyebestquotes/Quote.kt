package com.katsidzira.kanyebestquotes

import com.google.gson.annotations.SerializedName

data class Quote(
    @SerializedName("quote")
    private val quote: String
)

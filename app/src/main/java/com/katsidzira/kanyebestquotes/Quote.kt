package com.katsidzira.kanyebestquotes

import com.google.gson.annotations.SerializedName

data class Quote(
    @SerializedName("quote")
    private var quote: String
) {

    override fun toString(): String {
        return quote
    }
}


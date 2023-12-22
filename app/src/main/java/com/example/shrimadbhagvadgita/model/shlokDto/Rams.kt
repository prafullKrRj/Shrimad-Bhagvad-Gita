package com.example.shrimadbhagvadgita.model.shlokDto

import com.google.gson.annotations.SerializedName

data class Rams(
    @SerializedName("author")
    val author: String,
    @SerializedName("hc")
    val hc: String,
    @SerializedName("ht")
    val ht: String
)
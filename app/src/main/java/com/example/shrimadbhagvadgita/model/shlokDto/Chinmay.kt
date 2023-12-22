package com.example.shrimadbhagvadgita.model.shlokDto

import com.google.gson.annotations.SerializedName

data class Chinmay(
    @SerializedName("author")
    val author: String,
    @SerializedName("hc")
    val hc: String
)
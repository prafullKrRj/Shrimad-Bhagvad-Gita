package com.example.shrimadbhagvadgita.model.shlokDto

import com.google.gson.annotations.SerializedName

data class Tej(
    @SerializedName("author")
    val author: String,
    @SerializedName("ht")
    val ht: String
)
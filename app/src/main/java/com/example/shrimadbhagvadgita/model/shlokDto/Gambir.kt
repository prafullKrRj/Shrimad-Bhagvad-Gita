package com.example.shrimadbhagvadgita.model.shlokDto

import com.google.gson.annotations.SerializedName

data class Gambir(
    @SerializedName("author")
    val author: String,
    @SerializedName("et")
    val et: String
)
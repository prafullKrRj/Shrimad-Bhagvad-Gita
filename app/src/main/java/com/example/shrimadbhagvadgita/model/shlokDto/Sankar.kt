package com.example.shrimadbhagvadgita.model.shlokDto

import com.google.gson.annotations.SerializedName

data class Sankar(
    @SerializedName("author")
    val author: String,
    @SerializedName("et")
    val et: String,
    @SerializedName("sc")
    val sc: String,
    @SerializedName("ht")
    val ht: String
)
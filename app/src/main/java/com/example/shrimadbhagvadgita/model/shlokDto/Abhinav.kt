package com.example.shrimadbhagvadgita.model.shlokDto

import com.google.gson.annotations.SerializedName

data class Abhinav(
    @SerializedName("author")
    val author: String,
    @SerializedName("et")
    val et: String,
    @SerializedName("sc")
    val sc: String
)
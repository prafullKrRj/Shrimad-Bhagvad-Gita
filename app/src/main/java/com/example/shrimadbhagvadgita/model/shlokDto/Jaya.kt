package com.example.shrimadbhagvadgita.model.shlokDto

import com.google.gson.annotations.SerializedName

data class Jaya(
    @SerializedName("author")
    val author: String,
    @SerializedName("sc")
    val sc: String
)
package com.example.shrimadbhagvadgita.model.shlokDto

import com.google.gson.annotations.SerializedName

data class Siva(
    @SerializedName("author")
    val author: String,
    @SerializedName("ec")
    val ec: String,
    @SerializedName("et")
    val et: String
)
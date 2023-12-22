package com.example.shrimadbhagvadgita.model.shlokDto

import com.google.gson.annotations.SerializedName

data class San(
    @SerializedName("author")
    val author: String,
    @SerializedName("et")
    val et: String
)
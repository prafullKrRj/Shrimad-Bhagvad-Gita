package com.example.shrimadbhagvadgita.model.shlokDto

import com.google.gson.annotations.SerializedName

data class Ms(
    @SerializedName("author")
    val author: String,
    @SerializedName("sc")
    val sc: String
)
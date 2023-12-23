package com.example.shrimadbhagvadgita.model.shlokDto

data class Abhinav(
    val author: String,
    var et: String? = null,
    var sc: String? = null,
    var ec: String? = null,
    var hc: String? = null,
    var ht: String? = null,
) {
    fun toAuthorDetails(): AuthorDetails{
        return AuthorDetails(
            author = this.author,
            et = this.et,
            sc = this.sc,
            ec = this.ec,
            hc = this.hc,
            ht = this.ht,
        )
    }
}

data class AuthorDetails (
    var author: String,
    val et: String? = null,
    val sc: String? = null,
    val ec: String? = null,
    val hc: String? = null,
    val ht: String? = null,
)
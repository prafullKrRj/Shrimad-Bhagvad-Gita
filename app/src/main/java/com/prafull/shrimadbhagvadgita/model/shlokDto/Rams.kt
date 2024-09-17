package com.prafull.shrimadbhagvadgita.model.shlokDto

data class Rams(
    val author: String,
    var et: String? = null,
    var sc: String? = null,
    var ec: String? = null,
    var hc: String? = null,
    var ht: String? = null,
) {
    fun toAuthorDetails(): AuthorDetails {
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
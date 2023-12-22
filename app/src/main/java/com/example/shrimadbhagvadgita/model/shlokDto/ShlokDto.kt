package com.example.shrimadbhagvadgita.model.shlokDto

import com.google.gson.annotations.SerializedName

data class ShlokDto(
    @SerializedName("_id")
    val _id: String,
    @SerializedName("abhinav")
    val abhinav: Abhinav,
    @SerializedName("adi")
    val adi: Adi,
    @SerializedName("anand")
    val anand: Anand,
    @SerializedName("chapter")
    val chapter: Int,
    @SerializedName("chinmay")
    val chinmay: Chinmay,
    @SerializedName("dhan")
    val dhan: Dhan,
    @SerializedName("gambir")
    val gambir: Gambir,
    @SerializedName("jaya")
    val jaya: Jaya,
    @SerializedName("madhav")
    val madhav: Madhav,
    @SerializedName("ms")
    val ms: Ms,
    @SerializedName("neel")
    val neel: Neel,
    @SerializedName("purohit")
    val purohit: Purohit,
    @SerializedName("puru")
    val puru: Puru,
    @SerializedName("raman")
    val raman: Raman,
    @SerializedName("rams")
    val rams: Rams,
    @SerializedName("san")
    val san: San,
    @SerializedName("sankar")
    val sankar: Sankar,
    @SerializedName("siva")
    val siva: Siva,
    @SerializedName("slok")
    val slok: String,
    @SerializedName("srid")
    val srid: Srid,
    @SerializedName("tej")
    val tej: Tej,
    @SerializedName("transliteration")
    val transliteration: String,
    @SerializedName("vallabh")
    val vallabh: Vallabh,
    @SerializedName("venkat")
    val venkat: Venkat,
    @SerializedName("verse")
    val verse: Int
)

class TypeConverter {

}
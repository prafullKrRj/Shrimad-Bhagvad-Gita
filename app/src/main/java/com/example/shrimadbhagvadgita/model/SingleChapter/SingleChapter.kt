package com.example.shrimadbhagvadgita.model.SingleChapter

data class SingleChapter(
    val chapter_number: Int,
    val meaning: Meaning,
    val name: String,
    val summary: Summary,
    val translation: String,
    val transliteration: String,
    val verses_count: Int
)
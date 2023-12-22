package com.example.shrimadbhagvadgita.model.ChaptersCombined

class Chapters: ArrayList<ChaptersModelDto>()

data class ChaptersModelDto(
    val chapterNumber: Int,
    val meaning: ChaptersMeaning,
    val name: String,
    val summary: ChaptersSummary,
    val translation: String,
    val transliteration: String,
    val versesCount: Int
)
data class ChaptersMeaning(
    val en: String,
    val hi: String
)
data class ChaptersSummary(
    val en: String,
    val hi: String
)
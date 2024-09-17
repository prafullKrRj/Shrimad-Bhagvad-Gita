package com.prafull.shrimadbhagvadgita.data

import android.content.Context
import android.util.Log
import com.prafull.shrimadbhagvadgita.model.ChaptersCombined.Chapters
import com.prafull.shrimadbhagvadgita.model.SingleChapter.SingleChapter
import com.prafull.shrimadbhagvadgita.model.shlokDto.ShlokDto
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface BhagvatGitaRepository {
    suspend fun getShloks(chapter: Int, totalVerses: Int): List<ShlokDto>
    suspend fun getChapters(): Chapters
    suspend fun getSingleChapter(chapter: Int): SingleChapter
}

class BhagvatGitaRepositoryImpl(
    private val context: Context
) : BhagvatGitaRepository {
    override suspend fun getShloks(chapter: Int, totalVerses: Int): MutableList<ShlokDto> {
        val list: MutableList<ShlokDto> = mutableListOf()
        for (slok in 1..totalVerses) {
            Log.d("prafull", "getShloks: $chapter")
            val data = readJsonData(context, "slok/${chapter}/${slok}/index.json")
            list.add(Gson().fromJson(data, ShlokDto::class.java))
        }
        return list
    }

    override suspend fun getChapters(): Chapters {
        val data = readJsonData(context, "chapters/chapters.json")
        return Gson().fromJson(data, Chapters::class.java)
    }

    override suspend fun getSingleChapter(chapter: Int): SingleChapter {
        val data = readJsonData(context, "chapter/${chapter}/index.json")
        return Gson().fromJson(data, SingleChapter::class.java)
    }
}

suspend fun readJsonData(context: Context, fileName: String): String =
    withContext(Dispatchers.IO) {
        val inputStream = context.assets.open(fileName)
        val jsonString = inputStream.bufferedReader().use { it.readText() }
        inputStream.close()
        jsonString
    }
package com.cicd.jsonparser

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL

class JsonParser : ViewModel() {
    private val url =
        "https://jsonmock.hackerrank.com/api/articles?author=<authorName>&page=<pageNumber>"


    suspend fun getTitle(author: String, pageNum:Int = 1): ArrayList<String> { // epaga
        val updatedUrl = url.replace("<authorName>", author).replace("<pageNumber>", "$pageNum")
        val jsonString = getJson(updatedUrl)
        val list = ArrayList<String>()
        val articles = Gson().fromJson(jsonString, Articles::class.java)
        val dataArray = articles.data
        for (i in 0 until dataArray.size) {
            val data = dataArray[i]
            val title = data.title
            val storyTitle = data.story_title

            println("Title: $title")
            println("Story Title: $storyTitle")
            if (!title.isNullOrEmpty()) {
                list.add(title)
            } else if (!storyTitle.isNullOrEmpty()) {
                list.add(storyTitle)
            }
        }


        return list
    }

    private suspend fun getJson(urlString: String): String {

        val deferred = viewModelScope.async(Dispatchers.IO) {
            val url = URL(urlString)
            val connection = url.openConnection()
            val inputStream = connection.getInputStream()
            val ipReader = InputStreamReader(inputStream)
            val reader = BufferedReader(ipReader)
            val response = StringBuilder()

            var line: String?
            while (reader.readLine().also { line = it } != null) {
                response.append(line)
            }

            reader.close()
            response.toString()

        }

        return deferred.await()
    }
}

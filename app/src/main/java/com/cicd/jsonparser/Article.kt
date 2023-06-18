package com.cicd.jsonparser

import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("url")
    val url: String,
    @SerializedName("author")
    val author: String,
    @SerializedName("num_comments")
    val num_comments: Int,
    @SerializedName("story_id")
    val story_id: Int? = null,
    @SerializedName("story_title")
    val story_title: String? = null,
    @SerializedName("story_url")
    val story_url: String? = null,
    @SerializedName("parent_id")
    val parent_id: Int? = null,
    @SerializedName("created_at")
    val created_at: Long
)


data class Articles(
    @SerializedName("page")
    val page: Int,
    @SerializedName("per_page")
    val perPage: Int,
    @SerializedName("total")
    val total: Int,
    @SerializedName("total_pages")
    val total_pages: Int,
    @SerializedName("data")
    val data:ArrayList<Data>
)